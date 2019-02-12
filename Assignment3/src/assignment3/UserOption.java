/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author nils
 */
public enum UserOption{
        QUIT, SHOW, CIRCLE, RECTANGLE, MOVE, REMOVE, SORT, HELP;
        
        public static UserOption toOption(String s){
            return valueOf(s.toUpperCase());
        }
        
        public static boolean isOption(String s){
            try {
                valueOf(s.toUpperCase());
                return true;
            } catch (EnumConstantNotPresentException e) {
                return false;
            }
        }
    }
