package java.lang.reflect;

import java.lang.annotation.ElementType;
import java.util.Set;
import static java.lang.annotation.ElementType.*;

/**
 * tl;dr -- JVM modifier flag. Related to but different than
 * java.lang.Modifier
 *
 * The values for the constants
 * representing the modifiers are taken from the tables in sections
 * {@jvms 4.1}, {@jvms 4.4}, {@jvms 4.5}, and {@jvms 4.7} of
 * <cite>The Java Virtual Machine Specification</cite>.
 *
 * @jls 8.1.1 Class Modifiers
 * @jls 8.3.1 Field Modifiers
 * @jls 8.4.3 Method Modifiers
 * @jls 8.8.3 Constructor Modifiers
 * @jls 9.1.1 Interface Modifiers
 * @see java.lang.reflect.Modifier
 * @see javax.lang.model.element.Modifier
 */
public enum ModifierFlag {
    /**
     * The flag {@code public}/{@code ACC_PUBLIC}.
     */
    PUBLIC(Modifier.PUBLIC, true, Set.of(TYPE, CONSTRUCTOR, METHOD, FIELD)),

    /**
     * The flag {@code protected}/{@code ACC_PROTECTED}.
     */
    PROTECTED(Modifier.PROTECTED, true, Set.of(CONSTRUCTOR, METHOD, FIELD)),

    /**
     * The flag {@code private}/{@code ACC_PRIVATE}.
     */
    PRIVATE(Modifier.PRIVATE, true, Set.of(CONSTRUCTOR, METHOD, FIELD)),

    /**
     * The flag {@code abstract}/{@code ACC_ABSTRACT}.
     */
    ABSTRACT(Modifier.ABSTRACT, true, Set.of(TYPE, METHOD)),

//     /**
//      * The flag {@code default}.
//      */
//      DEFAULT,

    /**
     * The flag {@code static}/{@code ACC_STATIC}.
     */
    STATIC(Modifier.STATIC, true, Set.of(FIELD, METHOD)),

//     /**
//      * The flag {@code sealed}
//      * @since 15
//      */
//     SEALED,

//     /**
//      * The flag {@code non-sealed}
//      * @since 15
//      */
//     NON_SEALED {
//         public String toString() {
//             return "non-sealed";
//         }
//     },

    /**
     * The flag {@code final}/{@code ACC_FINAL}.
     */
    FINAL(Modifier.FINAL, true, Set.of(FIELD, METHOD, PARAMETER, TYPE)),

    /**
     * The flag {@code transient}/{@code ACC_TRANSIENT}.
     */
    TRANSIENT(Modifier.TRANSIENT, true, Set.of(FIELD)),

    /**
     * The flag {@code volatile}/{@code ACC_VOLATILE}.
     */
    VOLATILE(Modifier.VOLATILE, true, Set.of(FIELD)),

    /**
     * The flag {@code synchronized}/{@code ACC_SYNCHRONIZED}.
     */
    SYNCHRONIZED(Modifier.SYNCHRONIZED, true, Set.of(METHOD, CONSTRUCTOR)),

    /**
     * The flag {@code native}/{@code ACC_NATIVE}.
     */
    NATIVE(Modifier.NATIVE, true, Set.of(METHOD)),

    /**
      * The {@code ACC_INTERFACE} flag.
      */
    INTERFACE(Modifier.INTERFACE, false, Set.of(TYPE)),

    /**
     * The flag {@code strictfp}/{@code ACC_STRICT}.
     */
    STRICT(Modifier.STRICT, true, Set.of(METHOD, CONSTRUCTOR)),

    /**
     * The flag {@code ACC_BRIDGE}
     */
    BRIDGE(0x00000040, false, Set.of(METHOD, CONSTRUCTOR)),

    /**
     * The flag {@code ACC_VARARGS}.
     */
    VARARGS(0x00000080, false, Set.of(METHOD, CONSTRUCTOR)),

    /**
     * The flag {@code ACC_SYNTHETIC}.
     */
    SYNTHETIC(0x00001000, false, Set.of(TYPE, FIELD, METHOD, CONSTRUCTOR, ElementType.MODULE, PARAMETER)),

    /**
     * The flag {@code ACC_MANDATED}.
     */
    MANDATED(0x00008000, false, Set.of(ElementType.MODULE, PARAMETER)),

    /**
     * The flag {@code ACC_ANNOTATION}.
     */
    ANNOTATION(0x00002000, false, Set.of(TYPE)),

    /**
     * The flag {@code ACC_ENUM}.
     */
    ENUM(0x00004000, false, Set.of(TYPE, FIELD)),

    /**
     * The flag {@code ACC_MODULE}.
     */
    MODULE(0x8000, false, Set.of(TYPE))
    ;

//     /**
//      * The Java source modifiers that can be applied to a class.
//      * @jls 8.1.1 Class Modifiers
//      */
//     private static final int CLASS_MODIFIERS =
//         Modifier.PUBLIC         | Modifier.PROTECTED    | Modifier.PRIVATE |
//         Modifier.ABSTRACT       | Modifier.STATIC       | Modifier.FINAL   |
//         Modifier.STRICT;

//     /**
//      * The Java source modifiers that can be applied to an interface.
//      * @jls 9.1.1 Interface Modifiers
//      */
//     private static final int INTERFACE_MODIFIERS =
//         Modifier.PUBLIC         | Modifier.PROTECTED    | Modifier.PRIVATE |
//         Modifier.ABSTRACT       | Modifier.STATIC       | Modifier.STRICT;


//     /**
//      * The Java source modifiers that can be applied to a constructor.
//      * @jls 8.8.3 Constructor Modifiers
//      */
//     private static final int CONSTRUCTOR_MODIFIERS =
//         Modifier.PUBLIC         | Modifier.PROTECTED    | Modifier.PRIVATE;

//     /**
//      * The Java source modifiers that can be applied to a method.
//      * @jls 8.4.3  Method Modifiers
//      */
//     private static final int METHOD_MODIFIERS =
//         Modifier.PUBLIC         | Modifier.PROTECTED    | Modifier.PRIVATE |
//         Modifier.ABSTRACT       | Modifier.STATIC       | Modifier.FINAL   |
//         Modifier.SYNCHRONIZED   | Modifier.NATIVE       | Modifier.STRICT;

//     /**
//      * The Java source modifiers that can be applied to a field.
//      * @jls 8.3.1 Field Modifiers
//      */
//     private static final int FIELD_MODIFIERS =
//         Modifier.PUBLIC         | Modifier.PROTECTED    | Modifier.PRIVATE |
//         Modifier.STATIC         | Modifier.FINAL        | Modifier.TRANSIENT |
//         Modifier.VOLATILE;

//     /**
//      * The Java source modifiers that can be applied to a method or constructor parameter.
//      * @jls 8.4.1 Formal Parameters
//      */
//     private static final int PARAMETER_MODIFIERS =
//         Modifier.FINAL;

//     static final int ACCESS_MODIFIERS =
//         Modifier.PUBLIC | Modifier.PROTECTED | Modifier.PRIVATE;


    private int mask;
    private boolean sourceModifier;

    // For now, reuse ElementType rather than defining a separate type.
    private Set<ElementType> targets;

    private ModifierFlag(Set<ElementType> targets) {
       this.mask = 0x0;
       this.sourceModifier = false;
       this.targets = targets;
    }

    private ModifierFlag(int mask, boolean sourceModifier, Set<ElementType> targets) {
        this.mask = mask;
        this.sourceModifier = sourceModifier;
        this.targets = targets;
    }

    /**
     * {@return corresponding integer mask for the flag; 0 if none}
     */
    public int mask() {
        return mask;
    }

    /**
     * {@return whether or not the flag has a directly corresponding
     * modifier in the Java programming language}
     */
    public boolean sourceModifer() {
        return sourceModifier;
    }

    /**
     * {@return kinds of constructs the flag can be applied to}
     */
    public Set<ElementType> targets() {
        return targets;
    }

    /**
     * Returns this modifier's name as defined in <cite>The
     * Java Language Specification</cite>.
     * The flag name is the {@linkplain #name() name of the enum
     * constant} in lowercase and with any underscores ("{@code _}")
     * replaced with hyphens ("{@code -}").
     * @return the modifier's name
     */
    @Override
    public String toString() {
        return name().toLowerCase(java.util.Locale.US);
    }
}