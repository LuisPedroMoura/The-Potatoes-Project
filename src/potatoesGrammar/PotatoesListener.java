// Generated from Potatoes.g4 by ANTLR 4.7.1

	package potatoesGrammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PotatoesParser}.
 */
public interface PotatoesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(PotatoesParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(PotatoesParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#using}.
	 * @param ctx the parse tree
	 */
	void enterUsing(PotatoesParser.UsingContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#using}.
	 * @param ctx the parse tree
	 */
	void exitUsing(PotatoesParser.UsingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code code_Declaration}
	 * labeled alternative in {@link PotatoesParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode_Declaration(PotatoesParser.Code_DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code code_Declaration}
	 * labeled alternative in {@link PotatoesParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode_Declaration(PotatoesParser.Code_DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code code_Assignment}
	 * labeled alternative in {@link PotatoesParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode_Assignment(PotatoesParser.Code_AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code code_Assignment}
	 * labeled alternative in {@link PotatoesParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode_Assignment(PotatoesParser.Code_AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code code_Function}
	 * labeled alternative in {@link PotatoesParser#code}.
	 * @param ctx the parse tree
	 */
	void enterCode_Function(PotatoesParser.Code_FunctionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code code_Function}
	 * labeled alternative in {@link PotatoesParser#code}.
	 * @param ctx the parse tree
	 */
	void exitCode_Function(PotatoesParser.Code_FunctionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statement_Declaration}
	 * labeled alternative in {@link PotatoesParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement_Declaration(PotatoesParser.Statement_DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statement_Declaration}
	 * labeled alternative in {@link PotatoesParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement_Declaration(PotatoesParser.Statement_DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statement_Assignment}
	 * labeled alternative in {@link PotatoesParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement_Assignment(PotatoesParser.Statement_AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statement_Assignment}
	 * labeled alternative in {@link PotatoesParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement_Assignment(PotatoesParser.Statement_AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statement_Control_Flow_Statement}
	 * labeled alternative in {@link PotatoesParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement_Control_Flow_Statement(PotatoesParser.Statement_Control_Flow_StatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statement_Control_Flow_Statement}
	 * labeled alternative in {@link PotatoesParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement_Control_Flow_Statement(PotatoesParser.Statement_Control_Flow_StatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statement_FunctionCall}
	 * labeled alternative in {@link PotatoesParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement_FunctionCall(PotatoesParser.Statement_FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statement_FunctionCall}
	 * labeled alternative in {@link PotatoesParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement_FunctionCall(PotatoesParser.Statement_FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statement_Function_Return}
	 * labeled alternative in {@link PotatoesParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement_Function_Return(PotatoesParser.Statement_Function_ReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statement_Function_Return}
	 * labeled alternative in {@link PotatoesParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement_Function_Return(PotatoesParser.Statement_Function_ReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statement_Print}
	 * labeled alternative in {@link PotatoesParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement_Print(PotatoesParser.Statement_PrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statement_Print}
	 * labeled alternative in {@link PotatoesParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement_Print(PotatoesParser.Statement_PrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declaration_array}
	 * labeled alternative in {@link PotatoesParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration_array(PotatoesParser.Declaration_arrayContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declaration_array}
	 * labeled alternative in {@link PotatoesParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration_array(PotatoesParser.Declaration_arrayContext ctx);
	/**
	 * Enter a parse tree produced by the {@code declaration_Var}
	 * labeled alternative in {@link PotatoesParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration_Var(PotatoesParser.Declaration_VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code declaration_Var}
	 * labeled alternative in {@link PotatoesParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration_Var(PotatoesParser.Declaration_VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_Var_Declaration_Not_Boolean}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_Var_Declaration_Not_Boolean(PotatoesParser.Assignment_Var_Declaration_Not_BooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_Var_Declaration_Not_Boolean}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_Var_Declaration_Not_Boolean(PotatoesParser.Assignment_Var_Declaration_Not_BooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_Var_Declaration_Value}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_Var_Declaration_Value(PotatoesParser.Assignment_Var_Declaration_ValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_Var_Declaration_Value}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_Var_Declaration_Value(PotatoesParser.Assignment_Var_Declaration_ValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_Var_Declaration_Comparison}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_Var_Declaration_Comparison(PotatoesParser.Assignment_Var_Declaration_ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_Var_Declaration_Comparison}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_Var_Declaration_Comparison(PotatoesParser.Assignment_Var_Declaration_ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_Var_Declaration_Operation}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_Var_Declaration_Operation(PotatoesParser.Assignment_Var_Declaration_OperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_Var_Declaration_Operation}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_Var_Declaration_Operation(PotatoesParser.Assignment_Var_Declaration_OperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_Var_Declaration_FunctionCall}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_Var_Declaration_FunctionCall(PotatoesParser.Assignment_Var_Declaration_FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_Var_Declaration_FunctionCall}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_Var_Declaration_FunctionCall(PotatoesParser.Assignment_Var_Declaration_FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_Var_Not_Boolean}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_Var_Not_Boolean(PotatoesParser.Assignment_Var_Not_BooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_Var_Not_Boolean}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_Var_Not_Boolean(PotatoesParser.Assignment_Var_Not_BooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_Var_Value}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_Var_Value(PotatoesParser.Assignment_Var_ValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_Var_Value}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_Var_Value(PotatoesParser.Assignment_Var_ValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_Var_Comparison}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_Var_Comparison(PotatoesParser.Assignment_Var_ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_Var_Comparison}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_Var_Comparison(PotatoesParser.Assignment_Var_ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_Var_Operation}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_Var_Operation(PotatoesParser.Assignment_Var_OperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_Var_Operation}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_Var_Operation(PotatoesParser.Assignment_Var_OperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_Var_ValueList}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_Var_ValueList(PotatoesParser.Assignment_Var_ValueListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_Var_ValueList}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_Var_ValueList(PotatoesParser.Assignment_Var_ValueListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assingment_Var_FunctionCall}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssingment_Var_FunctionCall(PotatoesParser.Assingment_Var_FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assingment_Var_FunctionCall}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssingment_Var_FunctionCall(PotatoesParser.Assingment_Var_FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_Array_ValuesList}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_Array_ValuesList(PotatoesParser.Assignment_Array_ValuesListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_Array_ValuesList}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_Array_ValuesList(PotatoesParser.Assignment_Array_ValuesListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_Array_FunctionCall}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_Array_FunctionCall(PotatoesParser.Assignment_Array_FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_Array_FunctionCall}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_Array_FunctionCall(PotatoesParser.Assignment_Array_FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assigmennt_Array_Var}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssigmennt_Array_Var(PotatoesParser.Assigmennt_Array_VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assigmennt_Array_Var}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssigmennt_Array_Var(PotatoesParser.Assigmennt_Array_VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_ArrayAccess_Not_Boolean}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_ArrayAccess_Not_Boolean(PotatoesParser.Assignment_ArrayAccess_Not_BooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_ArrayAccess_Not_Boolean}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_ArrayAccess_Not_Boolean(PotatoesParser.Assignment_ArrayAccess_Not_BooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_ArrayAccess_Value}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_ArrayAccess_Value(PotatoesParser.Assignment_ArrayAccess_ValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_ArrayAccess_Value}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_ArrayAccess_Value(PotatoesParser.Assignment_ArrayAccess_ValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_ArrayAccess_Comparison}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_ArrayAccess_Comparison(PotatoesParser.Assignment_ArrayAccess_ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_ArrayAccess_Comparison}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_ArrayAccess_Comparison(PotatoesParser.Assignment_ArrayAccess_ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_ArrayAccess_Operation}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_ArrayAccess_Operation(PotatoesParser.Assignment_ArrayAccess_OperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_ArrayAccess_Operation}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_ArrayAccess_Operation(PotatoesParser.Assignment_ArrayAccess_OperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignment_ArrayAccess_ValueList}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment_ArrayAccess_ValueList(PotatoesParser.Assignment_ArrayAccess_ValueListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignment_ArrayAccess_ValueList}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment_ArrayAccess_ValueList(PotatoesParser.Assignment_ArrayAccess_ValueListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assingment_ArrayAccess_FunctionCall}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssingment_ArrayAccess_FunctionCall(PotatoesParser.Assingment_ArrayAccess_FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assingment_ArrayAccess_FunctionCall}
	 * labeled alternative in {@link PotatoesParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssingment_ArrayAccess_FunctionCall(PotatoesParser.Assingment_ArrayAccess_FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(PotatoesParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(PotatoesParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#functionReturn}.
	 * @param ctx the parse tree
	 */
	void enterFunctionReturn(PotatoesParser.FunctionReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#functionReturn}.
	 * @param ctx the parse tree
	 */
	void exitFunctionReturn(PotatoesParser.FunctionReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(PotatoesParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(PotatoesParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#controlFlowStatement}.
	 * @param ctx the parse tree
	 */
	void enterControlFlowStatement(PotatoesParser.ControlFlowStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#controlFlowStatement}.
	 * @param ctx the parse tree
	 */
	void exitControlFlowStatement(PotatoesParser.ControlFlowStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(PotatoesParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(PotatoesParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(PotatoesParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(PotatoesParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#when}.
	 * @param ctx the parse tree
	 */
	void enterWhen(PotatoesParser.WhenContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#when}.
	 * @param ctx the parse tree
	 */
	void exitWhen(PotatoesParser.WhenContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#whenCase}.
	 * @param ctx the parse tree
	 */
	void enterWhenCase(PotatoesParser.WhenCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#whenCase}.
	 * @param ctx the parse tree
	 */
	void exitWhenCase(PotatoesParser.WhenCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(PotatoesParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(PotatoesParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOperation_Operation}
	 * labeled alternative in {@link PotatoesParser#logicalOperation}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperation_Operation(PotatoesParser.LogicalOperation_OperationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOperation_Operation}
	 * labeled alternative in {@link PotatoesParser#logicalOperation}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperation_Operation(PotatoesParser.LogicalOperation_OperationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOperation_Parenthesis}
	 * labeled alternative in {@link PotatoesParser#logicalOperation}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperation_Parenthesis(PotatoesParser.LogicalOperation_ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOperation_Parenthesis}
	 * labeled alternative in {@link PotatoesParser#logicalOperation}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperation_Parenthesis(PotatoesParser.LogicalOperation_ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOperation_logicalOperand}
	 * labeled alternative in {@link PotatoesParser#logicalOperation}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperation_logicalOperand(PotatoesParser.LogicalOperation_logicalOperandContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOperation_logicalOperand}
	 * labeled alternative in {@link PotatoesParser#logicalOperation}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperation_logicalOperand(PotatoesParser.LogicalOperation_logicalOperandContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOperand_Comparison}
	 * labeled alternative in {@link PotatoesParser#logicalOperand}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperand_Comparison(PotatoesParser.LogicalOperand_ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOperand_Comparison}
	 * labeled alternative in {@link PotatoesParser#logicalOperand}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperand_Comparison(PotatoesParser.LogicalOperand_ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOperand_Not_Comparison}
	 * labeled alternative in {@link PotatoesParser#logicalOperand}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperand_Not_Comparison(PotatoesParser.LogicalOperand_Not_ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOperand_Not_Comparison}
	 * labeled alternative in {@link PotatoesParser#logicalOperand}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperand_Not_Comparison(PotatoesParser.LogicalOperand_Not_ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOperand_Var}
	 * labeled alternative in {@link PotatoesParser#logicalOperand}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperand_Var(PotatoesParser.LogicalOperand_VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOperand_Var}
	 * labeled alternative in {@link PotatoesParser#logicalOperand}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperand_Var(PotatoesParser.LogicalOperand_VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOperand_Not_Var}
	 * labeled alternative in {@link PotatoesParser#logicalOperand}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperand_Not_Var(PotatoesParser.LogicalOperand_Not_VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOperand_Not_Var}
	 * labeled alternative in {@link PotatoesParser#logicalOperand}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperand_Not_Var(PotatoesParser.LogicalOperand_Not_VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOperand_Value}
	 * labeled alternative in {@link PotatoesParser#logicalOperand}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperand_Value(PotatoesParser.LogicalOperand_ValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOperand_Value}
	 * labeled alternative in {@link PotatoesParser#logicalOperand}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperand_Value(PotatoesParser.LogicalOperand_ValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalOperand_Not_Value}
	 * labeled alternative in {@link PotatoesParser#logicalOperand}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOperand_Not_Value(PotatoesParser.LogicalOperand_Not_ValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalOperand_Not_Value}
	 * labeled alternative in {@link PotatoesParser#logicalOperand}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOperand_Not_Value(PotatoesParser.LogicalOperand_Not_ValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(PotatoesParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(PotatoesParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#compareOperator}.
	 * @param ctx the parse tree
	 */
	void enterCompareOperator(PotatoesParser.CompareOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#compareOperator}.
	 * @param ctx the parse tree
	 */
	void exitCompareOperator(PotatoesParser.CompareOperatorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operation_FunctionCall}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation_FunctionCall(PotatoesParser.Operation_FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operation_FunctionCall}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation_FunctionCall(PotatoesParser.Operation_FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operation_NUMBER}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation_NUMBER(PotatoesParser.Operation_NUMBERContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operation_NUMBER}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation_NUMBER(PotatoesParser.Operation_NUMBERContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operation_Simetric}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation_Simetric(PotatoesParser.Operation_SimetricContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operation_Simetric}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation_Simetric(PotatoesParser.Operation_SimetricContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operation_ArrayAccess}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation_ArrayAccess(PotatoesParser.Operation_ArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operation_ArrayAccess}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation_ArrayAccess(PotatoesParser.Operation_ArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operation_Parenthesis}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation_Parenthesis(PotatoesParser.Operation_ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operation_Parenthesis}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation_Parenthesis(PotatoesParser.Operation_ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operation_Cast}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation_Cast(PotatoesParser.Operation_CastContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operation_Cast}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation_Cast(PotatoesParser.Operation_CastContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operation_Mult_Div_Mod}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation_Mult_Div_Mod(PotatoesParser.Operation_Mult_Div_ModContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operation_Mult_Div_Mod}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation_Mult_Div_Mod(PotatoesParser.Operation_Mult_Div_ModContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operation_ArrayLength}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation_ArrayLength(PotatoesParser.Operation_ArrayLengthContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operation_ArrayLength}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation_ArrayLength(PotatoesParser.Operation_ArrayLengthContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operation_Power}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation_Power(PotatoesParser.Operation_PowerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operation_Power}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation_Power(PotatoesParser.Operation_PowerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operation_Var}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation_Var(PotatoesParser.Operation_VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operation_Var}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation_Var(PotatoesParser.Operation_VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code operation_Add_Sub}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation_Add_Sub(PotatoesParser.Operation_Add_SubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code operation_Add_Sub}
	 * labeled alternative in {@link PotatoesParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation_Add_Sub(PotatoesParser.Operation_Add_SubContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterArrayDeclaration(PotatoesParser.ArrayDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitArrayDeclaration(PotatoesParser.ArrayDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(PotatoesParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(PotatoesParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#arrayAccess}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccess(PotatoesParser.ArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#arrayAccess}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccess(PotatoesParser.ArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#arrayLength}.
	 * @param ctx the parse tree
	 */
	void enterArrayLength(PotatoesParser.ArrayLengthContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#arrayLength}.
	 * @param ctx the parse tree
	 */
	void exitArrayLength(PotatoesParser.ArrayLengthContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(PotatoesParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(PotatoesParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(PotatoesParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(PotatoesParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(PotatoesParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(PotatoesParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code type_Number_Type}
	 * labeled alternative in {@link PotatoesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType_Number_Type(PotatoesParser.Type_Number_TypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code type_Number_Type}
	 * labeled alternative in {@link PotatoesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType_Number_Type(PotatoesParser.Type_Number_TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code type_Boolean_Type}
	 * labeled alternative in {@link PotatoesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType_Boolean_Type(PotatoesParser.Type_Boolean_TypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code type_Boolean_Type}
	 * labeled alternative in {@link PotatoesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType_Boolean_Type(PotatoesParser.Type_Boolean_TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code type_String_Type}
	 * labeled alternative in {@link PotatoesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType_String_Type(PotatoesParser.Type_String_TypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code type_String_Type}
	 * labeled alternative in {@link PotatoesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType_String_Type(PotatoesParser.Type_String_TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code type_Void_Type}
	 * labeled alternative in {@link PotatoesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType_Void_Type(PotatoesParser.Type_Void_TypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code type_Void_Type}
	 * labeled alternative in {@link PotatoesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType_Void_Type(PotatoesParser.Type_Void_TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code type_ID_Type}
	 * labeled alternative in {@link PotatoesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType_ID_Type(PotatoesParser.Type_ID_TypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code type_ID_Type}
	 * labeled alternative in {@link PotatoesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType_ID_Type(PotatoesParser.Type_ID_TypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code type_ArrayType}
	 * labeled alternative in {@link PotatoesParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType_ArrayType(PotatoesParser.Type_ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code type_ArrayType}
	 * labeled alternative in {@link PotatoesParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType_ArrayType(PotatoesParser.Type_ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code value_Cast_Number}
	 * labeled alternative in {@link PotatoesParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue_Cast_Number(PotatoesParser.Value_Cast_NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code value_Cast_Number}
	 * labeled alternative in {@link PotatoesParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue_Cast_Number(PotatoesParser.Value_Cast_NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code value_Number}
	 * labeled alternative in {@link PotatoesParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue_Number(PotatoesParser.Value_NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code value_Number}
	 * labeled alternative in {@link PotatoesParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue_Number(PotatoesParser.Value_NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code value_Boolean}
	 * labeled alternative in {@link PotatoesParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue_Boolean(PotatoesParser.Value_BooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code value_Boolean}
	 * labeled alternative in {@link PotatoesParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue_Boolean(PotatoesParser.Value_BooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code value_String}
	 * labeled alternative in {@link PotatoesParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue_String(PotatoesParser.Value_StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code value_String}
	 * labeled alternative in {@link PotatoesParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue_String(PotatoesParser.Value_StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#valuesList}.
	 * @param ctx the parse tree
	 */
	void enterValuesList(PotatoesParser.ValuesListContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#valuesList}.
	 * @param ctx the parse tree
	 */
	void exitValuesList(PotatoesParser.ValuesListContext ctx);
	/**
	 * Enter a parse tree produced by {@link PotatoesParser#cast}.
	 * @param ctx the parse tree
	 */
	void enterCast(PotatoesParser.CastContext ctx);
	/**
	 * Exit a parse tree produced by {@link PotatoesParser#cast}.
	 * @param ctx the parse tree
	 */
	void exitCast(PotatoesParser.CastContext ctx);
}