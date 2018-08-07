package compiler;

import utils.*;
import utils.errorHandling.ErrorHandling;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.stringtemplate.v4.*;
import potatoesGrammar.PotatoesBaseVisitor;
import potatoesGrammar.PotatoesParser.*;
import typesGrammar.TypesFileInfo;

/**
 * * <b>PotatoesCompiler</b><p>
 * 
 * @author Ines Justo (84804), Luis Pedro Moura (83808), Maria Joao Lavoura (84681), Pedro Teixeira (84715)
 * @version May-June 2018
 */
public class PotatoesCompiler extends PotatoesBaseVisitor<ST> {
	
	protected static STGroup stg = null;
	//protected static ParseTreeProperty<Object> mapCtxObj = PotatoesSemanticCheck.getMapCtxObj();

	protected static Map<String, String> symbolTableName = new HashMap<>();  // stores the updated name of variables
	protected static Map<String, Object> symbolTableValue = new HashMap<>(); // stores the updated value of variables
	
	private static TypesFileInfo typesFileInfo;
	private static Map<String, Type> typesTable;
	private static Type destinationType;
	
	private static int varCounter = 0;
	
	private static final boolean debug = false;
	
	// --------------------------------------------------------------------------------------------------------------------
	// MAIN RULES----------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------
	
	
	@Override
	public ST visitProgram(ProgramContext ctx) {
		stg = new STGroupFile("java.stg");
	    ST classContent = stg.getInstanceOf("class");
	    visit(ctx.using());
	    for(CodeContext context : ctx.code()) {
	    	classContent.add("stat", visit(context));
	    }
	    return classContent;
	}
	
	
	@Override
	public ST visitUsing(UsingContext ctx) {
		String str = ctx.STRING().getText();
		String path = str.substring(1, str.length() -1);
		typesFileInfo = new TypesFileInfo(path);
		typesTable = typesFileInfo.getTypesTable();
		return visitChildren(ctx);
	}

	
	@Override
	public ST visitCode_Declaration(Code_DeclarationContext ctx) {
		ST varDeclaration = visit(ctx.varDeclaration());
		return createEOL(varDeclaration);
	}
	
	
	@Override
	public ST visitCode_Assignment(Code_AssignmentContext ctx) {
		return visit(ctx.assignment());
	}

	@Override
	public ST visitCode_Function(Code_FunctionContext ctx) {
		return visit(ctx.function());
	}
	
	@Override
	public ST visitScope(ScopeContext ctx) {
		// Visit all statement rules
		ST scopeContent = stg.getInstanceOf("scope");
		for (StatementContext stat : ctx.statement()) {
			scopeContent.add("stat", visit(stat));
		}
		return scopeContent;
	}
	
	
	// --------------------------------------------------------------------------------------------------------------------	
	// CLASS - STATEMENTS--------------------------------------------------------------------------------------------------	
	// --------------------------------------------------------------------------------------------------------------------	
	@Override
	public ST visitStatement_Declaration(Statement_DeclarationContext ctx) {
		ST varDeclaration = visit(ctx.varDeclaration());
		return createEOL(varDeclaration);
	}

	
	@Override
	public ST visitStatement_Assignment(Statement_AssignmentContext ctx) {
		return visit(ctx.assignment());
	}

	
	@Override
	public ST visitStatement_Control_Flow_Statement(Statement_Control_Flow_StatementContext ctx) {
		return visit(ctx.controlFlowStatement());
	}
	
	@Override
	public ST visitStatement_FunctionCall(Statement_FunctionCallContext ctx) {
		return visit(ctx.functionCall());
	}
	
	@Override
	public ST visitStatement_Function_Return(Statement_Function_ReturnContext ctx) {
		return visit(ctx.functionReturn());
	}

	@Override
	public ST visitStatement_Print(Statement_PrintContext ctx) {
		return visit(ctx.print());
	}
	
	// --------------------------------------------------------------------------------------------------------------------
	// CLASS - ASSIGNMENTS-----------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------	
	
	
	@Override
	public ST visitAssignment_Var_Declaration_Not_Boolean(Assignment_Var_Declaration_Not_BooleanContext ctx) {
		
		// get varDeclaration info
		ST varDeclaration =  visit(ctx.varDeclaration());
		
		String type = (String)varDeclaration.getAttribute("type");
		String varOriginalName = ctx.varDeclaration().ID().getText();
		String varNewName = (String) varDeclaration.getAttribute("var");
		
		//get var assigned info
		String varOpOriginalName = ctx.var().getText();
		String varOpName = symbolTableName.get(varOpOriginalName);
		
		// create assignment ST
		ST assignment = varAssignmentST(type, varNewName, "! "+varOpName); 	
		
		// update
		updateSymbolTables(varOriginalName, varNewName, !(Boolean)getValueFromSymbolsTable(varOpName));
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> Assignment_Var_Declaration_Not_Boolean");
			ErrorHandling.printInfo(ctx,"\t-> assignment = "+assignment.render());
			ErrorHandling.printInfo(ctx,"");
		}
		
		return assignment;
	}

	
	@Override
	public ST visitAssignment_Var_Declaration_Value(Assignment_Var_Declaration_ValueContext ctx) {
		
		// get varDeclaration info
		ST varDeclaration =  visit(ctx.varDeclaration());
		
		String type = (String) varDeclaration.getAttribute("type");
		String varOriginalName = ctx.varDeclaration().ID().getText();	
		String varNewName = (String) varDeclaration.getAttribute("var");
		
		// get value info
		String value = ctx.value().getText();
		
		// create assignment ST
		ST assignment = varAssignmentST(type, varNewName); 
	
		if(type.equals("Double")) {
			
			// value returns a varAssignment ST
			ST valueST = visit(ctx.value());
			assignment.add("operation",valueST.getAttribute("operation"));
			
			Variable temp = (Variable) getValueFromSymbolsTable((String)valueST.getAttribute("var"));
			Variable val = new Variable(temp);
			updateSymbolTables(varOriginalName, varNewName, val);
		}
		else if(type.equals("String")) {
			String str = value;
			assignment.add("operation", str);
			updateSymbolTables(varOriginalName, varNewName, str);
		}
		else { //typeValue.equals("Boolean")
			Boolean b = Boolean.parseBoolean((ctx.value().getText()));
			assignment.add("operation", b);
			updateSymbolTables(varOriginalName, varNewName, b);
		}
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitAssignment_Var_Declaration_Value");
			ErrorHandling.printInfo(ctx,"\t-> type = "+type);
			ErrorHandling.printInfo(ctx,"\t-> assignment = "+assignment.render());
			ErrorHandling.printInfo(ctx,"");
		}
	
		return assignment;
	}
	
	
	@Override
	public ST visitAssignment_Var_Declaration_Comparison(Assignment_Var_Declaration_ComparisonContext ctx) {
			
		//get varDeclaration info
		ST varDeclaration =  visit(ctx.varDeclaration());
		String type = (String) varDeclaration.getAttribute("type");
		String varNewName = (String) varDeclaration.getAttribute("var");
		//get ST of comparison
		ST comparison = visit(ctx.comparison());
		
		
		//all the declarations until now
		String previousDec =  comparison.render();
		//assign the result var of comparison
		String resultVarName = (String) comparison.getAttribute("var");
		//create a ST for this assignment
		ST assignment = varAssignmentST(previousDec, type, varNewName, resultVarName);
					
		//get the var name in potatoes code 
		String originalName = ctx.varDeclaration().ID().getText();
		
		updateSymbolTables(originalName, varNewName, getValueFromSymbolsTable(resultVarName));
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitAssignment_Var_Declaration_Comparison");
			ErrorHandling.printInfo(ctx,"\t-> assignment = "+assignment.render());
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"");
		}
		
		return assignment;
	}

	
	@Override
	public ST visitAssignment_Var_Declaration_Operation(Assignment_Var_Declaration_OperationContext ctx) {
		String typeName = ctx.varDeclaration().type().getText();
		if(!typeName.equals("boolean") && !typeName.equals("string")) {
			destinationType = typesTable.get(typeName);
			destinationType.clearCheckList();
		}
		
		//get ST of var declaration
		ST varDeclaration =  visit(ctx.varDeclaration());
		
		//get ST of operation
		ST operation =  visit(ctx.operation());
				
		//get typeValue from var declaration ST 
		String type = (String) varDeclaration.getAttribute("type");
		//get the new var name of this assignment from var declaration ST 
		String varNewName = (String) varDeclaration.getAttribute("var");
		// all assignments until now
		String previousOp =  operation.render();
		//assign the operation
		String resultVarName = (String) operation.getAttribute("var");
		
		//create a ST for this assignment
		ST assignment = varAssignmentST(type, varNewName);
		assignment.add("stat", previousOp);
		
		String originalName = ctx.varDeclaration().ID().getText();
		
		
		
		if(!typeName.equals("number") && !typeName.equals("boolean") && !typeName.equals("string")) {
			Variable temp = (Variable)getValueFromSymbolsTable(resultVarName);
			Variable a = new Variable(temp);
			a.convertTypeTo(destinationType);
			Double factor = Variable.getPathCost();
			assignment.add("operation", resultVarName+"*"+factor);
			destinationType.clearCheckList();
		}
		else assignment.add("operation", resultVarName);
		
		
		updateSymbolTables(originalName, varNewName, getValueFromSymbolsTable(resultVarName));
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitAssignment_Var_Declaration_Operation");
			ErrorHandling.printInfo(ctx,"\t-> assignment:\n"+assignment.render());
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"");
		}
		
		return assignment;	
	}

	@Override
	public ST visitAssignment_Var_Declaration_FunctionCall(Assignment_Var_Declaration_FunctionCallContext ctx) {
		// TODO Auto-generated method stub
		return visitChildren(ctx);
	}

	
	@Override
	public ST visitAssignment_Var_Not_Boolean(Assignment_Var_Not_BooleanContext ctx) {
		
		//get typeValue from var declaration ST 
		//String type = "Boolean";
		//get the new var name of this assignment from var declaration ST 
		String originalName = ctx.var(0).getText();	
		String varNewName = symbolTableName.get(originalName); //getNewVarName();

		String varOpOriginalName = ctx.var(1).getText();
		String varOpName = symbolTableName.get(varOpOriginalName);
		
		//create a ST for this assignment
		//ST assignment = varAssignmentST(type, varNewName, "! "+varOpName);

		
		ST assignment = stg.getInstanceOf("varAssignment");
		assignment.add("var", varNewName);
		assignment.add("operation",  "! "+varOpName);
		
		symbolTableValue.put(varNewName, !(Boolean)getValueFromSymbolsTable(varOpOriginalName));
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitAssignment_Var_Not_Boolean");
			ErrorHandling.printInfo(ctx,"\t-> assignment = "+assignment.render());
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"");
		}
		
		return assignment;
	}
	
	
	@Override
	public ST visitAssignment_Var_Value(Assignment_Var_ValueContext ctx) {
		
		//get the var name in potatoes code
		String originalName = ctx.var().getText();
	
		//create a ST for this assignment
		ST assignment = stg.getInstanceOf("varAssignment");
		String varNewName = symbolTableName.get(originalName);  //getNewVarName();
		assignment.add("var", varNewName);
		
		Object typeValue = getValueFromSymbolsTable(originalName);
		
		
		if(typeValue instanceof Boolean) {
			//assignment.add("type", "Boolean");
			Boolean b = Boolean.parseBoolean((ctx.value().getText()));
			assignment.add("operation", b);
			symbolTableValue.put(varNewName, b);
		}
		else if(typeValue instanceof String) {
			//assignment.add("type", "String");
			String s = ctx.value().getText();
			assignment.add("operation", s);
			symbolTableValue.put(varNewName, s);
		}
		else { //typeValue.equals("number")||typeValue.equals("ID")
			ST valueST = visit(ctx.value());
			
			//assignment.add("type", "Double");
			
			String operation = (String)valueST.getAttribute("operation");
			assignment.add("operation", operation);	
			
			Variable temp = (Variable) getValueFromSymbolsTable((String)valueST.getAttribute("var"));
			Variable a = new Variable(temp);
			
			symbolTableValue.put(varNewName, a);
		}

		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitAssignment_Var_Value");
			ErrorHandling.printInfo(ctx,"\t-> assignment = "+assignment.render());
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"");
		}
	
		return assignment;
	}

	
	@Override
	public ST visitAssignment_Var_Comparison(Assignment_Var_ComparisonContext ctx) {
		
		//get the var name in potatoes code
		String originalName = ctx.var().getText();
		String varNewName = symbolTableName.get(originalName);// getNewVarName();
				
		//get ST of comparison
		ST comparison = visit(ctx.comparison());
		//all the declarations until now
		String previousDec =  comparison.render();
		//assign the result var of comparison
		String comparisonVarName = (String) comparison.getAttribute("var");
		
		//get typeValue from var declaration ST 
		//String type = "Boolean";
		
		//create a ST for this assignment
		ST assignment = varAssignmentST(previousDec, "", varNewName, comparisonVarName);
		
		symbolTableValue.put(varNewName, getValueFromSymbolsTable(comparisonVarName));
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitAssignment_Var_Comparison");
			ErrorHandling.printInfo(ctx,"\t-> comparison = "+comparison.render());
			ErrorHandling.printInfo(ctx,"\t-> assignment = "+assignment.render());
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"");
		}
		
		return assignment;
	}

	
	@Override
	public ST visitAssignment_Var_Operation(Assignment_Var_OperationContext ctx) {
			
		//get the var name in potatoes code
		String originalName = ctx.var().getText();
		
		//create a ST for this assignment
		ST assignment = stg.getInstanceOf("varAssignment");
		
		//get the new var name
		String newVarName = symbolTableName.get(originalName);//getNewVarName();
		assignment.add("var", newVarName);
		
		//get ST of operation
		ST operation =  visit(ctx.operation());
		
		String operationName = (String) operation.getAttribute("var");
		
		//add all the other assignments until now
		assignment.add("stat", operation.render());

		
		Object obj = getValueFromSymbolsTable(operationName);
		
		
		if(obj instanceof Boolean) {
			//assignment.add("type", "Boolean");
			assignment.add("operation", operationName);
			symbolTableValue.put( newVarName, obj);
		}
		else if(obj instanceof String) {
			//assignment.add("type", "String");
			assignment.add("operation", operationName);
			symbolTableValue.put( newVarName, obj);
		}
		else { //typeValue.equals("number")||typeValue.equals("ID")
			
			//assignment.add("type", "Double");
			
			Variable temp = (Variable) obj;
			Variable a = new Variable(temp);
			String typeName = ((Variable)getValueFromSymbolsTable(originalName)).getType().getTypeName();
			destinationType = typesTable.get(typeName); 

			destinationType.clearCheckList();
									
			a.convertTypeTo(destinationType);
			
			Double factor = Variable.getPathCost();
			
			destinationType.clearCheckList();
			
			assignment.add("operation", operationName+"*"+factor);
			
			symbolTableValue.put( newVarName, a);
			
		}
	
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitAssignment_Var_Operation");
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"");
		}
		
		return assignment;
	}
	
	@Override
	public ST visitAssingment_Var_FunctionCall(Assingment_Var_FunctionCallContext ctx) {
		// TODO Auto-generated method stub
		return visitChildren(ctx);
	}
	
	// --------------------------------------------------------------------------------------------------------------------	
	// FUNCTIONS-----------------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------	
	
	@Override
	public ST visitFunction_Main(Function_MainContext ctx) {
		ST statements = stg.getInstanceOf("stats");
		 for(StatementContext context : ctx.statement()) {
			 statements.add("stat", visit(context));
		    }
		return statements;
	}
	
	@Override
	public ST visitFunctionReturn(FunctionReturnContext ctx) {
		// TODO Auto-generated method stub
		return visitChildren(ctx);
	}

	@Override
	public ST visitFunctionCall(FunctionCallContext ctx) {
		// TODO Auto-generated method stub
		return visitChildren(ctx);
	}
		

	
		
	// --------------------------------------------------------------------------------------------------------------------
	// CONTROL FLOW STATMENTS----------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------	
	

	// [IJ] - DONE
	@Override
	public ST visitControlFlowStatement(ControlFlowStatementContext ctx) {
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitControlFlowStatement");
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"");
		}
		
		return visitChildren(ctx);
	}

	// [IJ] - DONE
	@Override
	public ST visitForLoop(ForLoopContext ctx) {
		
		ST forLoop = stg.getInstanceOf("forLoop");
		
		//assignments
		int size = ctx.assignment().size();
		if(size!=1){// FOR '('assignment EOL logicalOperation EOL assignment ')'
			ST assignment = visit(ctx.assignment(0));
			forLoop.add("outsideStatements", assignment.render());
		}
		
		//logical operation
		ST logicalOperation = visit(ctx.logicalOperation());
		forLoop.add("outsideStatements", "//"+ctx.logicalOperation().getText());
		forLoop.add("outsideStatements", logicalOperation.render());
		String booleanVarName = (String) logicalOperation.getAttribute("var");
		forLoop.add("logicalOperation", "!"+booleanVarName);
		
		//content
		for(StatementContext context : ctx.statement()) {
			ST statements = visit(context);
			forLoop.add("content", statements.render());
		}
		
		forLoop.add("content", "\n//finalAssignment actualization");
		
		
		if(size==1) {// FOR '(' EOL logicalOperation EOL assignment ')'
			ST assignment0 = visit(ctx.assignment(0));
			forLoop.add("finalAssignment",assignment0.render() );
		}
		else {// FOR '('assignment EOL logicalOperation EOL assignment ')'
			ST assignment1 = visit(ctx.assignment(1));
			forLoop.add("finalAssignment", assignment1.render());
		}
		
		
		//logical operation
		ST logicalOperation1 = visit(ctx.logicalOperation());
		forLoop.add("finalAssignment", "//"+ctx.logicalOperation().getText());
		forLoop.add("finalAssignment", logicalOperation1.render());
		String booleanVarName1 = (String) logicalOperation1.getAttribute("var");
		String reAssignment = booleanVarName + "=" + booleanVarName1 + ";";
		forLoop.add("finalAssignment", reAssignment);
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitForLoop");
			ErrorHandling.printInfo(ctx,"\t-> forLoop.render()\n"+forLoop.render());
			ErrorHandling.printInfo(ctx,"------------------------------------------------");
			ErrorHandling.printInfo(ctx,"");
		}
		
		return forLoop;
	}

	// [IJ] - DONE
	@Override
	public ST visitWhileLoop(WhileLoopContext ctx) {

		ST whileLoop = stg.getInstanceOf("whileLoop");
		
		//logicalOperation
		ST logicalOperation = visit(ctx.logicalOperation());
		whileLoop.add("previousStatements", logicalOperation.render());
		String booleanVarName = (String) logicalOperation.getAttribute("var");
		whileLoop.add("logicalOperation", booleanVarName);
		//statements
		for(StatementContext context : ctx.statement()) {
			ST statements = visit(context);
			whileLoop.add("content", statements.render());
		}
		
		//logical operation
		ST logicalOperation1 = visit(ctx.logicalOperation());
		whileLoop.add("content", "//"+ctx.logicalOperation().getText());
		whileLoop.add("content", logicalOperation1.render());
		String booleanVarName1 = (String) logicalOperation1.getAttribute("var");
		String reAssignment = booleanVarName + "=" + booleanVarName1 + ";";
		whileLoop.add("content", reAssignment);

		return whileLoop;
	}
	
	@Override
	public ST visitCondition_withoutElse(Condition_withoutElseContext ctx) {
		ST condition = stg.getInstanceOf("stats");
		
		ST ifCondition = visit(ctx.ifCondition());
		
		ST elseIfCondition = stg.getInstanceOf("stats");
		
		for(ElseIfConditionContext context : ctx.elseIfCondition()) {
			ST temp = visit(context);
			String previousStatements = (String) temp.getAttribute("previousStatements");
			ifCondition.add("previousStatements", previousStatements);
			elseIfCondition.add("stat", temp.render().substring(previousStatements.length()));
		}
		
		condition.add("stat", ifCondition.render());
		condition.add("stat", elseIfCondition.render());
		
		return condition;
	}


	@Override
	public ST visitCondition_withElse(Condition_withElseContext ctx) {
		ST condition = stg.getInstanceOf("stats");
		
		ST ifCondition = visit(ctx.ifCondition());
		
		ST elseIfCondition = stg.getInstanceOf("stats");
		
		for(ElseIfConditionContext context : ctx.elseIfCondition()) {
			ST temp = visit(context);
			String previousStatements = (String) temp.getAttribute("previousStatements");
			ifCondition.add("previousStatements", previousStatements);
			elseIfCondition.add("stat", temp.render().substring(previousStatements.length()));
		}
			
		ST elseCondition = visit(ctx.elseCondition());
		
		condition.add("stat", ifCondition.render());
		condition.add("stat", elseIfCondition.render());
		condition.add("stat", elseCondition.render());
		
		return condition;
	}

	// [IJ] - DONE
	@Override 
	public ST visitIfCondition(IfConditionContext ctx) { 
		ST ifCondition = stg.getInstanceOf("ifCondition");
		
		//logicalOperation
		ST logicalOperation = visit(ctx.logicalOperation());
		ifCondition.add("previousStatements", logicalOperation.render());
		ifCondition.add("logicalOperation", logicalOperation.getAttribute("operation"));
		//statements
		for(StatementContext context : ctx.statement()) {
			ST statements = visit(context);
			ifCondition.add("content", statements.render());
		}
				
		return ifCondition;
	}

	// [IJ] - DONE
	@Override 
	public ST visitElseIfCondition(ElseIfConditionContext ctx) { 
		ST elseIfCondition = stg.getInstanceOf("elseIfCondition");
		
		//logicalOperation
		ST logicalOperation = visit(ctx.logicalOperation());
		elseIfCondition.add("previousStatements", logicalOperation.render());
		elseIfCondition.add("logicalOperation", logicalOperation.getAttribute("operation"));
		//statements
		for(StatementContext context : ctx.statement()) {
			ST statements = visit(context);
			elseIfCondition.add("content", statements.render());
		}
		
		return elseIfCondition;
	}

	// [IJ] -  DONE
	@Override 
	public ST visitElseCondition(ElseConditionContext ctx) { 
		ST elseCondition = stg.getInstanceOf("elseCondition");
		for(StatementContext context : ctx.statement()) {
			ST statements = visit(context);
			elseCondition.add("content", statements.render());
		}
		
		return elseCondition;
	}

	
	// --------------------------------------------------------------------------------------------------------------------
	// LOGICAL OPERATIONS--------------------------------------------------------------------------------------------------
	// --------------------------------------------------------------------------------------------------------------------
	
	
	@Override
	public ST visitLogicalOperation_Parenthesis(LogicalOperation_ParenthesisContext ctx) {
		return visit(ctx.logicalOperation());
	}

	
	@Override
	public ST visitLogicalOperation_Operation(LogicalOperation_OperationContext ctx) {
		ST op0 = visit(ctx.logicalOperation(0));
		ST op1 = visit(ctx.logicalOperation(1));

		String nameVar0 = (String) op0.getAttribute("var");
		String nameVar1 = (String) op1.getAttribute("var");
		
		String op = ctx.op.getText();

		String type = "Boolean";
		String varNewName = getNewVarName();
		String logicalOperation = nameVar0 + op + nameVar1;
		ST assign = varAssignmentST(type, varNewName, logicalOperation);
		
		//all the declarations done until now
		assign.add("stat", op0.render());
		assign.add("stat", op1.render());
		
		Boolean b0 = (Boolean) getValueFromSymbolsTable(nameVar0);
		Boolean b1 = (Boolean) getValueFromSymbolsTable(nameVar1);
		
		symbolTableValue.put( varNewName, getLogicOperationResult(b0,b1,op));
		
		return assign;
	}

	
	@Override
	public ST visitLogicalOperation_logicalOperand(LogicalOperation_logicalOperandContext ctx) {
		return visit(ctx.logicalOperand());
	}

	
	@Override
	public ST visitLogicalOperand_Comparison(LogicalOperand_ComparisonContext ctx) {
		return visit(ctx.comparison());
	}

	
	@Override
	public ST visitLogicalOperand_Not_Comparison(LogicalOperand_Not_ComparisonContext ctx) {
		ST comparison = visit(ctx.comparison());
		String previousDec =  comparison.render();
		String comparisonVarName = (String) comparison.getAttribute("var");
	
		String type = "Boolean";
		String varNewName = getNewVarName();
		ST assignment = varAssignmentST(previousDec, type, varNewName, comparisonVarName);
		
		symbolTableValue.put( varNewName, !(Boolean)getValueFromSymbolsTable(comparisonVarName));
		
		return assignment;
	}

	
	@Override
	public ST visitLogicalOperand_Var(LogicalOperand_VarContext ctx) {
		String originalName = ctx.var().getText();
		String type = "Boolean";
		String varNewName = getNewVarName();
		String resultVarName = symbolTableName.get(originalName);
		ST assignment = varAssignmentST(type, varNewName, resultVarName);
		symbolTableValue.put(varNewName, getValueFromSymbolsTable(resultVarName));
		
		return assignment;
	}

	
	@Override
	public ST visitLogicalOperand_Not_Var(LogicalOperand_Not_VarContext ctx) {
		String originalName = ctx.var().getText();
		String type = "Boolean";
		String varNewName = getNewVarName();
		String resultVarName = symbolTableName.get(originalName);
		ST assignment = varAssignmentST(type, varNewName, resultVarName);
		symbolTableValue.put( varNewName, !(Boolean)getValueFromSymbolsTable(resultVarName));
		return assignment;
	}

	
	@Override
	public ST visitLogicalOperand_Value(LogicalOperand_ValueContext ctx) {
		Boolean value = Boolean.parseBoolean(ctx.value().getText());
		String type = "Boolean";
		String varNewName = getNewVarName();
		ST assignment = varAssignmentST(type, varNewName, value+"");
		symbolTableValue.put( varNewName, value);
		
		return assignment;
	}

	
	@Override
	public ST visitLogicalOperand_Not_Value(LogicalOperand_Not_ValueContext ctx) {
		String value = ctx.value().getText();
		String type = "Boolean";
		String varNewName = getNewVarName();
		ST assignment = varAssignmentST(type, varNewName, value);
		symbolTableValue.put( varNewName, !Boolean.parseBoolean(value));
		
		return assignment;
	}

	
	@Override
	public ST visitComparison(ComparisonContext ctx) {
		ST op0 = visit(ctx.compareOperation(0));
		ST op1 = visit(ctx.compareOperation(1));
		
		String varNameOp0 =  (String) op0.getAttribute("var");
		String varNameOp1 =  (String) op1.getAttribute("var");

		String compareOp = ctx.compareOperator().getText();
		String comparison = varNameOp0 + compareOp + varNameOp1;
		if(compareOp.equals("==")) {
			comparison = varNameOp0 + ".equals(" + varNameOp1 + ")";
		}else if(compareOp.equals("!=")) {
			comparison = "!" + varNameOp0 + ".equals(" + varNameOp1 + ")";
		}else {
			comparison = varNameOp0 + compareOp + varNameOp1;
		}
		
		String type = "Boolean";
		String varNewName = getNewVarName();
		
		ST assignment = varAssignmentST(type, varNewName, comparison);
		
		assignment.add("stat",(String) op0.render());
		assignment.add("stat",(String) op1.render());
		
		
		String typeOp0 = (String)op0.getAttribute("type");
		if(typeOp0.equals("Boolean")) {
			Boolean b0 = (Boolean) getValueFromSymbolsTable(varNameOp0);
			Boolean b1 = (Boolean) getValueFromSymbolsTable(varNameOp1);
			
			symbolTableValue.put( varNewName, getBooleanResult(b0,b1,compareOp));
		}
		else {
			
			Variable temp = (Variable) getValueFromSymbolsTable(varNameOp0);
			Variable v0 = new Variable(temp);
			temp = (Variable) getValueFromSymbolsTable(varNameOp1);
			Variable v1 = new Variable(temp);
			symbolTableValue.put( varNewName, getBooleanResult(v0.getValue(),v1.getValue(),compareOp));
		}
		

		if(debug) {
			ErrorHandling.printInfo(ctx,"");;
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitComparison");
			ErrorHandling.printInfo(ctx,"\t-> assignment = "+assignment.render());
			ErrorHandling.printInfo(ctx,"");
		}
			
		return assignment;
	}	
	
	@Override
	public ST visitCompareOperation_Operation(CompareOperation_OperationContext ctx) {
		return super.visitCompareOperation_Operation(ctx);
	}

	@Override
	public ST visitCompareOperation_BOOLEAN(CompareOperation_BOOLEANContext ctx) {
		Boolean b = Boolean.parseBoolean(ctx.BOOLEAN().getText());
		String newName = getNewVarName();
		ST newVariable = varAssignmentST("Boolean", newName, b+""); 
		
		symbolTableValue.put( newName, b);
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitCompareOperation_BOOLEAN");
			ErrorHandling.printInfo(ctx,"");
		}
		
		return newVariable;
	}

	@Override
	public ST visitCompareOperator(CompareOperatorContext ctx) {
		return visitChildren(ctx);
	}

	// --------------------------------------------------------------------------------------------------------------------
	// OPERATIONS----------------------------------------------------------------------------------------------------------	
	// --------------------------------------------------------------------------------------------------------------------
	

	@Override
	public ST visitOperation_Cast(Operation_CastContext ctx) {	
		ST oldVariable = visit(ctx.operation());
		
		String oldVariableName = (String)oldVariable.getAttribute("var");
		
		Variable temp = (Variable) getValueFromSymbolsTable(oldVariableName);
		Variable a = new Variable(temp);
		
		String castType = (String) visit(ctx.cast()).getAttribute("operation");
		
		a.convertTypeTo(typesTable.get(castType));
		
		Double factor = Variable.getPathCost();
		
		ST newVariable = varAssignmentST("Double", getNewVarName(), oldVariableName+"*"+factor); 

		String newName = (String) newVariable.getAttribute("var");
		
		newVariable.add("stat", oldVariable.render());//all the declarations until now
		
		symbolTableValue.put( newName, a);
		
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitOperation_Cast");
			ErrorHandling.printInfo(ctx,"\t-> oldVar = "+oldVariable.render());
			ErrorHandling.printInfo(ctx,"\t-> newVar = "+newVariable.render());
			ErrorHandling.printInfo(ctx,"");
		}
		
		return newVariable;
	}		
	
	
	@Override
	public ST visitOperation_Parenthesis(Operation_ParenthesisContext ctx) {
		return visit(ctx.operation());
	}
                                                                    
	
	@Override
	public ST visitOperation_Mult_Div_Mod(Operation_Mult_Div_ModContext ctx) {
		
		ST op0 = visit(ctx.operation(0));
		ST op1 = visit(ctx.operation(1));
	
		String op0Name = (String)op0.getAttribute("var");
		String op1Name = (String)op1.getAttribute("var");
		
		Variable varOp0  = null;
		Variable varOp1  = null;
		Variable result = null;
		Double factorOp0 = null;
		Double factorOp1 = null;
		
		Variable temp = (Variable) getValueFromSymbolsTable(op0Name);
		varOp0 = new Variable(temp);
		temp = (Variable) getValueFromSymbolsTable(op1Name);
		varOp1 = new Variable(temp);
		
		ST newVariable = varAssignmentST( "Double", getNewVarName());
		String newName = (String) newVariable.getAttribute("var");
			
		//all the declarations done until now
		newVariable.add("stat", op0.render());
		newVariable.add("stat", op1.render());
		
		String op = ctx.op.getText();
		
		if (op.equals("%")) {
			Double moddedValue = varOp0.getValue() % varOp0.getValue();
			result = new Variable (typesTable.get(varOp0.getType().getTypeName()), moddedValue);
			newVariable.add("operation", op0Name + " % " + op1Name);
		}
		else {
			try {
				varOp0.MultDivCheckConvertType(destinationType);
			} catch (Exception e) {}
			factorOp0 = Variable.getPathCost();
			try {
				varOp1.MultDivCheckConvertType(destinationType);
			} catch (Exception e) {}
			factorOp1 = Variable.getPathCost();			
			
			if (op.equals("*")) {
				result = Variable.multiply(varOp0, varOp1); 
				Double resCode = result.getType().getCode(); 
				Collection<Type> types = typesTable.values(); 
				for (Type t : types) { 
					if (t.getCode() == resCode) {
						result = new Variable(typesTable.get(t.getTypeName()), result.getValue());
						break; 
					} 
				} 
			
				newVariable.add("operation", "("+op0Name+"*"+factorOp0+")" + " * " + "("+op1Name+"*"+factorOp1+")");
			}
			else if (op.equals("/")) {
				result = Variable.divide(varOp0, varOp1); 
				Double resCode = result.getType().getCode(); 
				Collection<Type> types = typesTable.values(); 
				for (Type t : types) { 
					if (t.getCode() == resCode) { 
						result = new Variable(typesTable.get(t.getTypeName()), result.getValue());
						break; 
					} 
				} 
				
				newVariable.add("operation", "("+op0Name+"*"+factorOp0+")" + " / " +  "("+op1Name+"*"+factorOp1+")");
			}
			else
				assert false: "missing semantic check";
		}		

		symbolTableValue.put( newName, result);
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitOperation_Mult_Div_Mod");
			ErrorHandling.printInfo(ctx,"\t-> op0 = "+op0.render());
			ErrorHandling.printInfo(ctx,"\t-> op1 = "+op1.render());
			ErrorHandling.printInfo(ctx,"\t-> newVar = "+newVariable.render());
			ErrorHandling.printInfo(ctx,"");
		}
		
		return newVariable;
	}

	
	@Override
	public ST visitOperation_Simetric(Operation_SimetricContext ctx) {
		
		ST previousVariable = visit(ctx.operation());
		
		String previousVariableName = (String)previousVariable.getAttribute("var");
		
		Variable temp = (Variable) getValueFromSymbolsTable(previousVariableName);
		Variable a = new Variable(temp);
		Variable.simetric(a);
		
		ST newVariable = varAssignmentST("Double", getNewVarName(), "- "+previousVariableName); 
		String newName = (String) newVariable.getAttribute("var");
		
		//add all the declarations until now
		newVariable.add("stat", previousVariable.render());
		
		symbolTableValue.put( newName, a);
		
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitOperation_Simetric");
			ErrorHandling.printInfo(ctx,"\t-> op0 = "+previousVariable.render());
			ErrorHandling.printInfo(ctx,"\t-> newVar = "+newVariable.render());
			ErrorHandling.printInfo(ctx,"");
		}
		
		return newVariable;
	}
	
	
	@Override
	public ST visitOperation_Add_Sub(Operation_Add_SubContext ctx) {
		if (destinationType == null) {
			destinationType = typesTable.get("number");
		}
		destinationType.clearCheckList();
		ST op0 = visit(ctx.operation(0));
		destinationType.clearCheckList();
		ST op1 = visit(ctx.operation(1));
		destinationType.clearCheckList();
		
		String op0Name = (String)op0.getAttribute("var");
		String op1Name = (String)op1.getAttribute("var");
		
		Variable varOp0  = null;
		Variable varOp1  = null;
		Variable result = null;
	
		Variable temp = (Variable) getValueFromSymbolsTable(op0Name);
		varOp0 = new Variable(temp);
		temp = (Variable) getValueFromSymbolsTable(op1Name);
		varOp1 = new Variable(temp); 
		
		ST newVariable = varAssignmentST("Double", getNewVarName()); 
		String newName = (String) newVariable.getAttribute("var");
		
		//all the declarations done until now
		newVariable.add("stat", op0.render());
		newVariable.add("stat", op1.render());
	
		
		
		
		varOp1.convertTypeTo(varOp0.getType());
		Double factor = Variable.getPathCost();
		
		
		if (ctx.op.getText().equals("+")) {
			result = Variable.add(varOp0, varOp1);
			newVariable.add("operation", op0Name + " + " + op1Name+"*"+factor);
		}
		else {//if (ctx.op.getText().equals("-")) {
			result = Variable.subtract(varOp0, varOp1);
			newVariable.add("operation", op0Name + " - " + op1Name+"*"+factor);
		}
		
		symbolTableValue.put(newName, result);
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitOperation_Add_Sub");
			ErrorHandling.printInfo(ctx,"\t-> newVar = "+newVariable.render());
			ErrorHandling.printInfo(ctx,"");
		}
		
		return newVariable;
		
	}


	@Override
	public ST visitOperation_Power(Operation_PowerContext ctx) {
			
		ST op0 = visit(ctx.operation(0));
		ST op1 = visit(ctx.operation(1));
		
		String nameVar0 = (String) op0.getAttribute("var");
		String nameVar1 = (String) op1.getAttribute("var");
		
		ST newVariable = varAssignmentST("Double", getNewVarName(), "Math.pow(" + nameVar0 + "," + nameVar1 + ");"); 
		String newName = (String) newVariable.getAttribute("var");
		
		newVariable.add("stat", op0.render());
		newVariable.add("stat", op1.render());

		Variable temp = (Variable) getValueFromSymbolsTable(nameVar0);
		Variable a = new Variable(temp);
		temp = (Variable) getValueFromSymbolsTable(nameVar1);
		Variable b = new Variable(temp);
		
		symbolTableValue.put( newName, Variable.power(a, b));
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitOperation_Power");
			ErrorHandling.printInfo(ctx,"\t-> op0 = "+op0.render());
			ErrorHandling.printInfo(ctx,"\t-> op1 = "+op1.render());
			ErrorHandling.printInfo(ctx,"\t-> newVar = "+newVariable.render());
			ErrorHandling.printInfo(ctx,"");
		}
		
		return newVariable;		
	}
	
	
	@Override
	public ST visitOperation_Var(Operation_VarContext ctx) {
		
		String varOpOriginalName = ctx.var().getText(); 
		String varOpNewName = symbolTableName.get(varOpOriginalName);	
		
		//create a ST for this assignment
		ST newVariable = stg.getInstanceOf("varAssignment");
		String newVariableName = getNewVarName();
		newVariable.add("var", newVariableName);
		
		Object value = getValueFromSymbolsTable(varOpOriginalName);
		
		if(value instanceof Boolean) {
			newVariable.add("type", "Boolean");
			
		}
		else if(value instanceof String) {
			newVariable.add("type", "String");
		}
		else { //typeValue.equals("number")||typeValue.equals("ID")
			newVariable.add("type", "Double");
		}

		newVariable.add("operation", varOpNewName);
		symbolTableValue.put( newVariableName, value);

		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitOperation_Var");
			ErrorHandling.printInfo(ctx,"\t-> varOpOriginalName = "+varOpOriginalName);
			ErrorHandling.printInfo(ctx,"\t-> varOpOriginalName = "+varOpNewName);
			ErrorHandling.printInfo(ctx,"\t-> newVar = "+newVariable.render());
			ErrorHandling.printInfo(ctx,"\t-> symbolTableName.get(varOpOriginalName) = "+symbolTableName.get(varOpOriginalName));
			ErrorHandling.printInfo(ctx,"\t-> getValueFromSymbolsTable(varOpOriginalName) = "+getValueFromSymbolsTable(varOpOriginalName));
			ErrorHandling.printInfo(ctx,"");
		}		
		
		return newVariable;
	}

	@Override
	public ST visitOperation_FunctionCall(Operation_FunctionCallContext ctx) {
		// TODO Auto-generated method stub
		return visitChildren(ctx);
	}
	
	
	@Override
	public ST visitOperation_NUMBER(Operation_NUMBERContext ctx) {
		
		String number = ctx.NUMBER().getText();
		Variable numberVar = createNumberVariable(number);
		ST newVariable = varAssignmentST( "Double", getNewVarName(), numberVar.getValue()+"");
		String newName = (String) newVariable.getAttribute("var");
		symbolTableValue.put( newName, numberVar);
		
		//ErrorHandling.printInfo(ctx,"newVariable.getAttribute(var) = " + newVariable.getAttribute("var"));
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitOperation_NUMBER");;
			ErrorHandling.printInfo(ctx,"\t-> newVar = "+newVariable.render());
			ErrorHandling.printInfo(ctx,"\t-> numberVar = " + numberVar);
			ErrorHandling.printInfo(ctx,"\t-> getValueFromSymbolsTable(newName) = "+(Variable) getValueFromSymbolsTable(newName));
			
			ErrorHandling.printInfo(ctx,"");
		}	
		
		return newVariable;
	}

	
	// FIXME this is repeated code, must be joined in one function
	@Override
	public ST visitPrint_Print(Print_PrintContext ctx) {
		//create printST
		ST print = stg.getInstanceOf("print");
		print.add("type", "print");
		
		// add all printVAr Sts to print ST
		int size = ctx.printVar().size();
		int i = 1;
		for(PrintVarContext toPrint : ctx.printVar()) {
			print.add("valueOrVarList", visit(toPrint).render());
			if(i<size) print.add("valueOrVarList", "+");
			i++;
		}
			
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitPrint_Print");
			ErrorHandling.printInfo(ctx,"\t-> print = "+ print.render());
			ErrorHandling.printInfo(ctx,"");
		}
		
		return print;
	}
	
	@Override
	public ST visitPrint_Println(Print_PrintlnContext ctx) {
		// create print ST
		ST print = stg.getInstanceOf("print");
		print.add("type", "println");
		
		// add all printVar STs to print ST
		int size = ctx.printVar().size();
		int i = 1;
		for(PrintVarContext toPrint : ctx.printVar()) {
			print.add("valueOrVarList", visit(toPrint).render());
			if(i<size) print.add("valueOrVarList", "+");
			i++;
		}
		
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitPrint_Println");
			ErrorHandling.printInfo(ctx,"\t-> print = "+ print.render());
			ErrorHandling.printInfo(ctx,"");
		}
		
		return print;
	}
	
	@Override
	public ST visitPrintVar_Var(PrintVar_VarContext ctx) {
		// get var info
		String varOriginalName = ctx.var().getText();
		String newVarName = symbolTableName.get(varOriginalName);
		
		// create var ST
		ST varST = stg.getInstanceOf("values");
		
		// get Object and add to ST accordingly
		Object obj = symbolTableValue.get(newVarName);
		
		if(obj instanceof Boolean) {
			varST.add("value", newVarName);
		}
		else if(obj instanceof String) {
			varST.add("value", newVarName);
		}
		else { // obj instanceof Variable
			Variable a = (Variable) obj;
			varST.add("value", newVarName + " " + a.getType().getPrintName());
		}
		return varST;
	}
	
	@Override
	public ST visitPrintVar_Value(PrintVar_ValueContext ctx) {
		String value = ctx.value().getText();
		ST valueST = stg.getInstanceOf("values");
		valueST.add("value", "\"\"+"+value+"+\"\"");
		return valueST;
	}
	
	// --------------------------------------------------------------------------------------------------------------------
	// VARS AND TYPES------------------------------------------------------------------------------------------------------ 
	// --------------------------------------------------------------------------------------------------------------------
	
	// TODO verify if this is enough
	@Override
	public ST visitVar(VarContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public ST visitVarDeclaration(VarDeclarationContext ctx) {
		// get varDeclaration info
		ST type = visit(ctx.type());
		String newVarName = getNewVarName();
		
		// create varDeclaration ST
		ST varDeclaration = stg.getInstanceOf("varDeclaration");
		varDeclaration.add("type", type.render());
		varDeclaration.add("var",newVarName );

		symbolTableValue.put(newVarName, null);
		
		return varDeclaration;
	}
	
	@Override
	public ST visitType_Number_Type(Type_Number_TypeContext ctx) {
		ST type = stg.getInstanceOf("type");
		type.add("type", "number");
		return type;
	}

	@Override
	public ST visitType_Boolean_Type(Type_Boolean_TypeContext ctx) {
		ST type = stg.getInstanceOf("type");
		type.add("type", "boolean");
		return type;
	}

	@Override
	public ST visitType_String_Type(Type_String_TypeContext ctx) {
		ST type = stg.getInstanceOf("type");
		type.add("type", "string");
		return type;
	}

	@Override
	public ST visitType_Void_Type(Type_Void_TypeContext ctx) {
		ST type = stg.getInstanceOf("type");
		type.add("type", "void");
		return type;
	}

	@Override
	public ST visitType_ID_Type(Type_ID_TypeContext ctx) {
		ST type = stg.getInstanceOf("type");
		type.add("type", "id");
		return type;
	}

	@Override
	public ST visitValue_Cast_Number(Value_Cast_NumberContext ctx) {
		// get cast info
		String castType = (String) visit(ctx.cast()).getAttribute("operation");
		
		// get number info
		String number = ctx.NUMBER().getText();
		
		// create variable from number
		Variable a = createNumberVariable(number);
		
		// create the casted variable
		Variable b = new Variable(a);
		b.convertTypeTo(typesTable.get(castType));
		
		// get the cost of conversion
		double factor = Variable.pathCost(b, a);
		
		// create ST to the cast (explained in visitCast())
		String newName0 = getNewVarName();
		ST cast = varAssignmentST("Double", newName0, factor+";"); // include semicolon
		
		// create this ST
		String newName1 = getNewVarName();
		ST newVariable = varAssignmentST(cast.render(), "Double", newName1, number+"*"+factor); 
		
		symbolTableValue.put(newName1, b);
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitValue_Cast_Number");
			ErrorHandling.printInfo(ctx,"");
		}
		
		return newVariable;
	}
	
	@Override
	public ST visitValue_Number(Value_NumberContext ctx) {
		
		Variable number = createVariable("number", ctx.NUMBER().getText());
		String newName = getNewVarName();
		ST newVariable = varAssignmentST("Double", newName, number.getValue()+""); 
		
		symbolTableValue.put(newName, number);
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitValue_Cast_Number");
			ErrorHandling.printInfo(ctx,"");
		}
		
		return newVariable;
	}

	@Override
	public ST visitValue_Boolean(Value_BooleanContext ctx) {
		Boolean b = Boolean.parseBoolean(ctx.BOOLEAN().getText());
		String newName = getNewVarName();
		ST newVariable = varAssignmentST("Boolean", newName, b+""); 
		
		symbolTableValue.put( newName, b);
		
		if(debug) {
			ErrorHandling.printInfo(ctx,"");
			ErrorHandling.printInfo(ctx,"->"+ctx.getText());
			ErrorHandling.printInfo(ctx,"\t-> visitCompareOperation_BOOLEAN");
			ErrorHandling.printInfo(ctx,"");
		}
		
		return newVariable;
	}

	@Override
	public ST visitValue_String(Value_StringContext ctx) {
		// string is returned with quotation marks included
		ST str = stg.getInstanceOf("values");
		str.add("value", ctx.STRING().getText());
		return str;
	}

	@Override
	public ST visitCast(CastContext ctx) {
		// In java the cast is converted to a conversion factor between the units, as all are converted to Double
		// but this conversion has to be made up in the hierarchy because in here there is no access to the other variable
		ST castValue = stg.getInstanceOf("values");
		castValue.add("value", ctx.ID().getText());
		return castValue;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------
	//OTHER ONES---------------------------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------------------------------------

	protected static ST createEOL (ST temp) {
		String stat = temp.render()+";";
		ST statements = stg.getInstanceOf("stats");
		statements.add("stat", stat);
		return statements;		
	}
	protected static ST varAssignmentST(String previousStatements, String type, String var, String operation) {
		ST newVariable = stg.getInstanceOf("varAssignment");
		
		newVariable.add("previousStatements", previousStatements);
		newVariable.add("type", type);
		newVariable.add("var", var);
		newVariable.add("operation", operation);
		
		return newVariable;
	}

	protected static ST varAssignmentST(String type, String var, String operation) {
				
		ST newVariable = stg.getInstanceOf("varAssignment");
		newVariable.add("type", type);
		newVariable.add("var", var);
		newVariable.add("operation", operation);
		
		return newVariable;
	} 
	
	protected static ST varAssignmentST(String type, String var) {
		
		ST newVariable = stg.getInstanceOf("varAssignment");
		newVariable.add("type", type);
		newVariable.add("var", var);
		
		return newVariable;
	} 
	
	
	protected static Variable createNumberVariable(String d) {
		Double number = Double.parseDouble(d);
		Variable a = new Variable(typesTable.get("number"), number);
		
		if(debug) {
			ErrorHandling.printInfo("");
			ErrorHandling.printInfo("->createNumberVariable");
			ErrorHandling.printInfo("\t-> number = "+d);
			ErrorHandling.printInfo("\t-> var : "+a.getType().getTypeName()+" "+a.getValue());
			ErrorHandling.printInfo("");
		}
		
		return a;
	}
	
	protected static Variable createVariable(String destType, String doubleValue) {
		Type type = typesTable.get(destType);
		Double value = Double.parseDouble(doubleValue);
		return new Variable(new Type(typesTable.get(type.getTypeName())), value);
	}
	
	protected static Variable createVariable(Type type, String doubleValue) {
		Double value = Double.parseDouble(doubleValue);
		return new Variable(type, value);
	}
		
	
	protected static void updateSymbolTables(String originalName,String newName, Object value) {
		symbolTableName.put(originalName, newName);
		symbolTableValue.put(newName, value);
	}
	
	protected static Object getValueFromSymbolsTable(String name) {
		Object obj = null;
		if(symbolTableName.containsKey(name)) { //name is original name
			String newName = symbolTableName.get(name);
			obj = symbolTableValue.get(newName);
		}
		else {
			obj = symbolTableValue.get(name);
		}
		return	obj;		
	}
	
	
	public static Boolean getBooleanResult(Object objOp0, Object objOp1, String op) {
		
		if (objOp0 instanceof Boolean) {
			Boolean b0 = (Boolean)objOp0;
			Boolean b1 = (Boolean)objOp1;
			switch(op) {
				case "==" : return b0 == b1; 
				case "!=" : return b0 != b1; 
			}
			assert false : "Semantic analysis faild";
			return false;
		}
	
		Double d0 = (Double)objOp0;
		Double d1 = (Double)objOp1;
		switch(op) {
			case "==" : return d0 == d1; 
			case "!=" : return d0 != d1; 
			case "<"  : return d0 < d1;
			case "<=" : return d0 <= d1; 
			case ">"  : return d0 > d1; 
			case ">=" : return d0 >= d1;
		}
		assert false : "Semantic analysis faild";
		return false;
	}
	
	
	public static Boolean getLogicOperationResult(Boolean booleanOp0, Boolean booleanOp1, String op) {
		switch(op) {
			case "&&" : return booleanOp0 && booleanOp1; 
			case "||" : return booleanOp0 || booleanOp1; 
		}
		return false;
	}
	
	
	public static String getNewVarName() {
		String newName = "var"+varCounter;
		varCounter++;
		return newName;
		
	}
	

	public static String getcCorrespondingType(String type) {
		switch(type) {
		case "number" : return "Double";
		case "string" : return "String";
		case "boolean" : return "Boolean";
		default : return "Double";
		}
	}
	
	/* (non-Javadoc)
	 * @see potatoesGrammar.PotatoesBaseVisitor#visitFunction_ID(potatoesGrammar.PotatoesParser.Function_IDContext)
	 */
	@Override
	public ST visitFunction_ID(Function_IDContext ctx) {
		// TODO Auto-generated method stub
		return super.visitFunction_ID(ctx);
	}

	
	
}

