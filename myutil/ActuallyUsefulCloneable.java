package myutil;

/**
 * The actually useful implementation of Cloneable interface
 * that enforces public clone() override.
 */
public interface ActuallyUsefulCloneable extends Cloneable {
    Object clone();
}