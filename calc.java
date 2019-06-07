package HW;

import java.util.*;


public class calc {
	

	 // Creating a stack 

    static Stack<Character> myStack = new Stack<Character>(); 

    static ArrayList<Character> myArray = new ArrayList<Character>(); 

    static Scanner userInput = new Scanner(System.in); 

     

    //  looks at the char variable to check for precedence. it return 2 or return 1 
       public static int precedence(char a){ 

            

           if(a == '/' || a == '%' || a == '*'){ 

                

               return 2; 

           } else 

               

               return 1; 

       } 

       

       //uses Arraylist of the Characters and returns values calculated by each case depending on user input 
       public static int postFixConversion(ArrayList<Character> a){ 

            

           Stack <Integer> infixStack = new Stack <Integer> (); 

           for(int i = 0; i < a.size(); i++){ 

                

               if(Character.isDigit(a.get(i))){ 

                   

                   infixStack.push(Character.getNumericValue(a.get(i))); 

               }  

                

               else{ 

                    

                   int second = infixStack.pop(); 

                   int first = infixStack.pop(); 

                   switch(a.get(i)){ 

                   case '+': infixStack.push(first + second); break; 

                   case '-': infixStack.push(first - second); break; 

                   case '*': infixStack.push(first * second); break; 

                   case '/': infixStack.push(first / second); break; 

                   case '%': infixStack.push(first % second); break; 

                   } 

               } 

           } 

           return infixStack.pop(); 

       } 

  

    public static void main(String[]args){ 

         
        // booleans to  say if there is a last operator or parenthisis
        boolean lastOperator = false; 

        boolean lastOpenParenthesis = false; 

        boolean lastClosedParenthesis = false; 

        //enter user infix 
           System.out.println("Enter an expression in infix or hit q to quit "); 

           String userInfixExp = userInput.nextLine(); 

           char[] infix = userInfixExp.toCharArray(); 

  

           // checks if there are operators between integers

           while (infix[infix.length-1] == '+' ||infix[infix.length-1] == '-' || infix[infix.length-1] == '*' || infix[infix.length-1] == '/' ||  infix[infix.length-1] == '%' ){ 

                

               System.out.println("Error in expression!! No operator between operands. Also last token must be an operand."); 

           } 

           
           //for loop to run through the infix stack to find all the integers and operands
           // does certain actions like error messages if you have to operations next to each other

           for(int i = 0; i < userInfixExp.length(); i++){ 

               while(infix[i] == ' '){ 

                   continue; 

               } 

               infix[i] = userInfixExp.charAt(i); 

               switch(infix[i]){ 

               

               // integers

               case '1':  

               case '2':  

               case '3':  

               case '4': 

               case '5':  

               case '6': 

               case '7':  

               case '8': 

               case '9':  

               case '0': 

                   lastOperator = false; 

                   lastOpenParenthesis = false; 

                   lastClosedParenthesis = false; 

                   myArray.add(infix[i]); 

                   break; 

               // ignores spaces

               case ' ': continue; 
               
               //checks for decimals
               case '.': System.out.println("Error in expression!! Cannot accept floating point numbers."); 

               // if you enter variable x, it will ask you to add a value to it

               case 'x':try{ 

                           System.out.println("Enter value of x: "); 

                           int xVarInt = userInput.nextInt(); 

                           String xVar = Integer.toString(xVarInt); 

                           myArray.add(xVar.charAt(0)); 

                           } catch(InputMismatchException e){ 

                           } 

                       break; 

                          

               // if it finds any of these operands, it will chack for certain errors

               case '+':  
            	   
               case '-':
            	   
               case '*': 

               case '/': 

               case '%': 

            	   //checks if operands are side by side
                       if (lastOperator){ 

                           System.out.println("Error in expression!! An operator cannot directly follow another operator."); 

                           System.exit(-1); 

                       } 
                       
                       //checks if (+
                       if (lastOpenParenthesis){ 

                           System.out.println("Error in expression!! No operator between operand and left parentheses."); 
                       } 

                       lastOperator = true; 

                       lastOpenParenthesis = false; 

                       lastClosedParenthesis = false; 

				while(!myStack.isEmpty() && myStack.peek() != '(' && 

                               precedence(infix[i]) <= precedence(myStack.peek())){ 

                           myArray.add(myStack.pop()); 

                       } 

                       myStack.push(infix[i]); 

                       break; 


               // checks for parenthisis

               case '(': myStack.push(infix[i]); 

                       lastOperator = false; 

                       lastOpenParenthesis = true; 

                       lastClosedParenthesis = false; 

				break; 

                       

               case ')': 
                       lastOperator = false; 

                       lastOpenParenthesis = false; 

                       lastClosedParenthesis = true; 

				try{ 

                           while(myStack.peek() != '('){ 

                               myArray.add(myStack.pop()); 

                           } 

                           myStack.pop(); 

                           break; 

                       } catch(EmptyStackException e){ 

                           System.out.println("Error in expression!! No matching left parentheses for a right parentheses"); 
                       } 

               

               // quit
               case 'q':System.exit(-1); 

               default: 
                   break; 

               

               } 

           } 

       
           //prints post fix expression 
           System.out.print("postfix: "); 

           for(int i = 0; i < myArray.size(); i++){ 

               System.out.print(myArray.get(i)); 

           } 

           System.out.println( postFixConversion(myArray)); 

           

       } 
    }