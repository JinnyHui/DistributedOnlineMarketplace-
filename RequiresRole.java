// CSCI507 Assignment 3, Mar 5
// Honor Pledge:
//
// I pledge that I have neither given nor
// received any help on this assignment.
//
// hui


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Component of Authorization pattern
 * Used for implementing role based access control
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresRole {
    String value();
}