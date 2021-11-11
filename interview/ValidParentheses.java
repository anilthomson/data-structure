package interview;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {
    private HashMap<Character, Character> mappings;
    ValidParentheses(){
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    } 
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        stack.push('c');
        for (int i = 0; i < s.length(); i++) {
          char c = s.charAt(i);
              if (this.mappings.containsKey(c)) {
                Character x =   stack.pop();
                  if(x==null) return false;
                if(mappings.get(c).compareTo(x)!=0){
                      return false;
                  }
              }else if (this.mappings.containsValue(c)) {
                   stack.push(c);
              }
        }
            stack.pop();
            return stack.isEmpty();
        }
}
