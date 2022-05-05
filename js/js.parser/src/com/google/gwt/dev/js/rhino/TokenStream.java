/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * The contents of this file are subject to the Netscape Public
 * License Version 1.1 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.mozilla.org/NPL/
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * The Original Code is Rhino code, released
 * May 6, 1999.
 *
 * The Initial Developer of the Original Code is Netscape
 * Communications Corporation.  Portions created by Netscape are
 * Copyright (C) 1997-1999 Netscape Communications Corporation. All
 * Rights Reserved.
 *
 * Contributor(s):
 * Roger Lawrence
 * Mike McCabe
 *
 * Alternatively, the contents of this file may be used under the
 * terms of the GNU Public License (the "GPL"), in which case the
 * provisions of the GPL are applicable instead of those above.
 * If you wish to allow use of your version of this file only
 * under the terms of the GPL and not to allow others to use your
 * version of this file under the NPL, indicate your decision by
 * deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL.  If you do not delete
 * the provisions above, a recipient may use your version of this
 * file under either the NPL or the GPL.
 */
// Modified by Google

package com.google.gwt.dev.js.rhino;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements the JavaScript scanner.
 * <p>
 * It is based on the C source files jsscan.c and jsscan.h
 * in the jsref package.
 */

public class TokenStream {

    private static final Map<String, Integer> KEYWORDS = new HashMap<String, Integer>();

    /*
     * JSTokenStream flags, mirroring those in jsscan.h.  These are used
     * by the parser to change/check the state of the scanner.
     */

    final static int
            TSF_NEWLINES = 1 << 0,  // tokenize newlines
            TSF_FUNCTION = 1 << 1,  // scanning inside function body
            TSF_RETURN_EXPR = 1 << 2,  // function has 'return expr;'
            TSF_RETURN_VOID = 1 << 3,  // function has 'return;'
            TSF_REGEXP = 1 << 4,  // looking for a regular expression
            TSF_DIRTYLINE = 1 << 5;  // stuff other than whitespace since
    // start of line

    /*
     * For chars - because we need something out-of-range
     * to check.  (And checking EOF by exception is annoying.)
     * Note distinction from EOF token type!
     */
    private final static int
            EOF_CHAR = -1;

    /**
     * Token types.  These values correspond to JSTokenType values in
     * jsscan.c.
     */

    public final static int
            // start enum
            ERROR = -1; // well-known as the only code < EOF
    public final static int EOF = 0;  // end of file token - (not EOF_CHAR)
    public final static int EOL = 1;  // end of line
    public final static int// Beginning here are interpreter bytecodes. Their values
            // must not exceed 127.
            POPV = 2;
    public final static int ENTERWITH = 3;
    public final static int LEAVEWITH = 4;
    public final static int RETURN = 5;
    public final static int GOTO = 6;
    public final static int IFEQ = 7;
    public final static int IFNE = 8;
    public final static int DUP = 9;
    public final static int SETNAME = 10;
    public final static int BITOR = 11;
    public final static int BITXOR = 12;
    public final static int BITAND = 13;
    public final static int EQ = 14;
    public final static int NE = 15;
    public final static int LT = 16;
    public final static int LE = 17;
    public final static int GT = 18;
    public final static int GE = 19;
    public final static int LSH = 20;
    public final static int RSH = 21;
    public final static int URSH = 22;
    public final static int ADD = 23;
    public final static int SUB = 24;
    public final static int MUL = 25;
    public final static int DIV = 26;
    public final static int MOD = 27;
    public final static int BITNOT = 28;
    public final static int NEG = 29;
    public final static int NEW = 30;
    public final static int DELPROP = 31;
    public final static int TYPEOF = 32;
    public final static int NAMEINC = 33;
    public final static int PROPINC = 34;
    public final static int ELEMINC = 35;
    public final static int NAMEDEC = 36;
    public final static int PROPDEC = 37;
    public final static int ELEMDEC = 38;
    public final static int GETPROP = 39;
    public final static int SETPROP = 40;
    public final static int GETELEM = 41;
    public final static int SETELEM = 42;
    public final static int CALL = 43;
    public final static int NAME = 44;
    public final static int NUMBER = 45;
    public final static int STRING = 46;
    public final static int ZERO = 47;
    public final static int ONE = 48;
    public final static int NULL = 49;
    public final static int THIS = 50;
    public final static int FALSE = 51;
    public final static int TRUE = 52;
    public final static int SHEQ = 53;   // shallow equality (===)
    public final static int SHNE = 54;   // shallow inequality (!==)
    public final static int CLOSURE = 55;
    public final static int REGEXP = 56;
    public final static int POP = 57;
    public final static int POS = 58;
    public final static int VARINC = 59;
    public final static int VARDEC = 60;
    public final static int BINDNAME = 61;
    public final static int THROW = 62;
    public final static int IN = 63;
    public final static int INSTANCEOF = 64;
    public final static int GOSUB = 65;
    public final static int RETSUB = 66;
    public final static int CALLSPECIAL = 67;
    public final static int GETTHIS = 68;
    public final static int NEWTEMP = 69;
    public final static int USETEMP = 70;
    public final static int GETBASE = 71;
    public final static int GETVAR = 72;
    public final static int SETVAR = 73;
    public final static int UNDEFINED = 74;
    public final static int TRY = 75;
    public final static int ENDTRY = 76;
    public final static int NEWSCOPE = 77;
    public final static int TYPEOFNAME = 78;
    public final static int ENUMINIT = 79;
    public final static int ENUMNEXT = 80;
    public final static int GETPROTO = 81;
    public final static int GETPARENT = 82;
    public final static int SETPROTO = 83;
    public final static int SETPARENT = 84;
    public final static int SCOPE = 85;
    public final static int GETSCOPEPARENT = 86;
    public final static int THISFN = 87;
    public final static int JTHROW = 88;
    public final static int// End of interpreter bytecodes
            SEMI = 89;  // semicolon
    public final static int LB = 90;  // left and right brackets
    public final static int RB = 91;
    public final static int LC = 92;  // left and right curlies (braces)
    public final static int RC = 93;
    public final static int LP = 94;  // left and right parentheses
    public final static int GWT = 95;
    public final static int COMMA = 96;  // comma operator
    public final static int ASSIGN = 97; // assignment ops (= += -= etc.)
    public final static int HOOK = 98; // conditional (?:)
    public final static int COLON = 99;
    public final static int OR = 100; // logical or (||)
    public final static int AND = 101; // logical and (&&)
    public final static int EQOP = 102; // equality ops (== !=)
    public final static int RELOP = 103; // relational ops (< <= > >=)
    public final static int SHOP = 104; // shift ops (<< >> >>>)
    public final static int UNARYOP = 105; // unary prefix operator
    public final static int INC = 106; // increment/decrement (++ --)
    public final static int DEC = 107;
    public final static int DOT = 108; // member operator (.)
    public final static int PRIMARY = 109; // true, false, null, this
    public final static int FUNCTION = 110; // function keyword
    public final static int EXPORT = 111; // export keyword
    public final static int IMPORT = 112; // import keyword
    public final static int IF = 113; // if keyword
    public final static int ELSE = 114; // else keyword
    public final static int SWITCH = 115; // switch keyword
    public final static int CASE = 116; // case keyword
    public final static int DEFAULT = 117; // default keyword
    public final static int WHILE = 118; // while keyword
    public final static int DO = 119; // do keyword
    public final static int FOR = 120; // for keyword
    public final static int BREAK = 121; // break keyword
    public final static int CONTINUE = 122; // continue keyword
    public final static int VAR = 123; // var keyword
    public final static int WITH = 124; // with keyword
    public final static int CATCH = 125; // catch keyword
    public final static int FINALLY = 126; // finally keyword

    public final static int /** Added by Mike - these are JSOPs in the jsref, but I
     * don't have them yet in the java implementation...
     * so they go here.  Also whatever I needed.
     * Most of these go in the 'op' field when returning
     * more general token types, eg. 'DIV' as the op of 'ASSIGN'.
     */
            NOP = 128; // NOP
    public final static int NOT = 129; // etc.
    public final static int PRE = 130; // for INC, DEC nodes.
    public final static int POST = 131;

    public final static int /**
     * For JSOPs associated with keywords...
     * eg. op = THIS; token = PRIMARY
     */

            VOID = 132;

    public final static int/* types used for the parse tree - these never get returned
     * by the scanner.
     */
            BLOCK = 133; // statement block
    public final static int ARRAYLIT = 134; // array literal
    public final static int OBJLIT = 135; // object literal
    public final static int LABEL = 136; // label
    public final static int TARGET = 137;
    public final static int LOOP = 138;
    public final static int ENUMDONE = 139;
    public final static int EXPRSTMT = 140;
    public final static int PARENT = 141;
    public final static int CONVERT = 142;
    public final static int JSR = 143;
    public final static int NEWLOCAL = 144;
    public final static int USELOCAL = 145;
    public final static int DEBUGGER = 146;
    public final static int SCRIPT = 147;   // top-level node for entire script

    public final static int LAST_TOKEN = 147;
    public final static int NUMBER_INT = 148;

    public final static int// This value is only used as a return value for getTokenHelper,
            // which is only called from getToken and exists to avoid an excessive
            // recursion problem if a number of lines in a row are comments.
            RETRY_TOKEN = 65535;

    // end enum


    public static String tokenToName(int token) {
        if (Context.printTrees || Context.printICode) {
            switch (token) {
                case ERROR:
                    return "error";
                case EOF:
                    return "eof";
                case EOL:
                    return "eol";
                case POPV:
                    return "popv";
                case ENTERWITH:
                    return "enterwith";
                case LEAVEWITH:
                    return "leavewith";
                case RETURN:
                    return "return";
                case GOTO:
                    return "goto";
                case IFEQ:
                    return "ifeq";
                case IFNE:
                    return "ifne";
                case DUP:
                    return "dup";
                case SETNAME:
                    return "setname";
                case BITOR:
                    return "bitor";
                case BITXOR:
                    return "bitxor";
                case BITAND:
                    return "bitand";
                case EQ:
                    return "eq";
                case NE:
                    return "ne";
                case LT:
                    return "lt";
                case LE:
                    return "le";
                case GT:
                    return "gt";
                case GE:
                    return "ge";
                case LSH:
                    return "lsh";
                case RSH:
                    return "rsh";
                case URSH:
                    return "ursh";
                case ADD:
                    return "add";
                case SUB:
                    return "sub";
                case MUL:
                    return "mul";
                case DIV:
                    return "div";
                case MOD:
                    return "mod";
                case BITNOT:
                    return "bitnot";
                case NEG:
                    return "neg";
                case NEW:
                    return "new";
                case DELPROP:
                    return "delprop";
                case TYPEOF:
                    return "typeof";
                case NAMEINC:
                    return "nameinc";
                case PROPINC:
                    return "propinc";
                case ELEMINC:
                    return "eleminc";
                case NAMEDEC:
                    return "namedec";
                case PROPDEC:
                    return "propdec";
                case ELEMDEC:
                    return "elemdec";
                case GETPROP:
                    return "getprop";
                case SETPROP:
                    return "setprop";
                case GETELEM:
                    return "getelem";
                case SETELEM:
                    return "setelem";
                case CALL:
                    return "call";
                case NAME:
                    return "name";
                case NUMBER_INT:
                    return "integer";
                case NUMBER:
                    return "double";
                case STRING:
                    return "string";
                case ZERO:
                    return "zero";
                case ONE:
                    return "one";
                case NULL:
                    return "null";
                case THIS:
                    return "this";
                case FALSE:
                    return "false";
                case TRUE:
                    return "true";
                case SHEQ:
                    return "sheq";
                case SHNE:
                    return "shne";
                case CLOSURE:
                    return "closure";
                case REGEXP:
                    return "object";
                case POP:
                    return "pop";
                case POS:
                    return "pos";
                case VARINC:
                    return "varinc";
                case VARDEC:
                    return "vardec";
                case BINDNAME:
                    return "bindname";
                case THROW:
                    return "throw";
                case IN:
                    return "in";
                case INSTANCEOF:
                    return "instanceof";
                case GOSUB:
                    return "gosub";
                case RETSUB:
                    return "retsub";
                case CALLSPECIAL:
                    return "callspecial";
                case GETTHIS:
                    return "getthis";
                case NEWTEMP:
                    return "newtemp";
                case USETEMP:
                    return "usetemp";
                case GETBASE:
                    return "getbase";
                case GETVAR:
                    return "getvar";
                case SETVAR:
                    return "setvar";
                case UNDEFINED:
                    return "undefined";
                case TRY:
                    return "try";
                case ENDTRY:
                    return "endtry";
                case NEWSCOPE:
                    return "newscope";
                case TYPEOFNAME:
                    return "typeofname";
                case ENUMINIT:
                    return "enuminit";
                case ENUMNEXT:
                    return "enumnext";
                case GETPROTO:
                    return "getproto";
                case GETPARENT:
                    return "getparent";
                case SETPROTO:
                    return "setproto";
                case SETPARENT:
                    return "setparent";
                case SCOPE:
                    return "scope";
                case GETSCOPEPARENT:
                    return "getscopeparent";
                case THISFN:
                    return "thisfn";
                case JTHROW:
                    return "jthrow";
                case SEMI:
                    return "semi";
                case LB:
                    return "lb";
                case RB:
                    return "rb";
                case LC:
                    return "lc";
                case RC:
                    return "rc";
                case LP:
                    return "lp";
                case GWT:
                    return "gwt";
                case COMMA:
                    return "comma";
                case ASSIGN:
                    return "assign";
                case HOOK:
                    return "hook";
                case COLON:
                    return "colon";
                case OR:
                    return "or";
                case AND:
                    return "and";
                case EQOP:
                    return "eqop";
                case RELOP:
                    return "relop";
                case SHOP:
                    return "shop";
                case UNARYOP:
                    return "unaryop";
                case INC:
                    return "inc";
                case DEC:
                    return "dec";
                case DOT:
                    return "dot";
                case PRIMARY:
                    return "primary";
                case FUNCTION:
                    return "function";
                case EXPORT:
                    return "export";
                case IMPORT:
                    return "import";
                case IF:
                    return "if";
                case ELSE:
                    return "else";
                case SWITCH:
                    return "switch";
                case CASE:
                    return "case";
                case DEFAULT:
                    return "default";
                case WHILE:
                    return "while";
                case DO:
                    return "do";
                case FOR:
                    return "for";
                case BREAK:
                    return "break";
                case CONTINUE:
                    return "continue";
                case VAR:
                    return "var";
                case WITH:
                    return "with";
                case CATCH:
                    return "catch";
                case FINALLY:
                    return "finally";
                case NOP:
                    return "nop";
                case NOT:
                    return "not";
                case PRE:
                    return "pre";
                case POST:
                    return "post";
                case VOID:
                    return "void";
                case BLOCK:
                    return "block";
                case ARRAYLIT:
                    return "arraylit";
                case OBJLIT:
                    return "objlit";
                case LABEL:
                    return "label";
                case TARGET:
                    return "target";
                case LOOP:
                    return "loop";
                case ENUMDONE:
                    return "enumdone";
                case EXPRSTMT:
                    return "exprstmt";
                case PARENT:
                    return "parent";
                case CONVERT:
                    return "convert";
                case JSR:
                    return "jsr";
                case NEWLOCAL:
                    return "newlocal";
                case USELOCAL:
                    return "uselocal";
                case SCRIPT:
                    return "script";
            }
            return "<unknown=" + token + ">";
        }
        return "";
    }

    /* This function uses the cached op, string and number fields in
     * TokenStream; if getToken has been called since the passed token
     * was scanned, the op or string printed may be incorrect.
     */
    public String tokenToString(int token) {
        if (Context.printTrees) {
            String name = tokenToName(token);

            switch (token) {
                case UNARYOP:
                case ASSIGN:
                case PRIMARY:
                case EQOP:
                case SHOP:
                case RELOP:
                    return name + " " + tokenToName(this.op);

                case STRING:
                case REGEXP:
                case NAME:
                    return name + " `" + this.string + "'";

                case NUMBER_INT:
                    return "NUMBER_INT " + (int) this.number;
                case NUMBER:
                    return "NUMBER " + this.number;
            }

            return name;
        }
        return "";
    }

    static {
        KEYWORDS.put("break", BREAK);
        KEYWORDS.put("case", CASE);
        KEYWORDS.put("continue", CONTINUE);
        KEYWORDS.put("default", DEFAULT);
        KEYWORDS.put("delete", DELPROP);
        KEYWORDS.put("do", DO);
        KEYWORDS.put("else", ELSE);
        KEYWORDS.put("export", EXPORT);
        KEYWORDS.put("false", PRIMARY | (FALSE << 8));
        KEYWORDS.put("for", FOR);
        KEYWORDS.put("function", FUNCTION);
        KEYWORDS.put("if", IF);
        KEYWORDS.put("in", RELOP | (IN << 8));
        KEYWORDS.put("new", NEW);
        KEYWORDS.put("null", PRIMARY | (NULL << 8));
        KEYWORDS.put("return", RETURN);
        KEYWORDS.put("switch", SWITCH);
        KEYWORDS.put("this", PRIMARY | (THIS << 8));
        KEYWORDS.put("true", PRIMARY | (TRUE << 8));
        KEYWORDS.put("typeof", UNARYOP | (TYPEOF << 8));
        KEYWORDS.put("var", VAR);
        KEYWORDS.put("void", UNARYOP | (VOID << 8));
        KEYWORDS.put("while", WHILE);
        KEYWORDS.put("with", WITH);
        KEYWORDS.put("catch", CATCH);
        KEYWORDS.put("debugger", DEBUGGER);
        KEYWORDS.put("finally", FINALLY);
        KEYWORDS.put("import", IMPORT);
        KEYWORDS.put("instanceof", RELOP | (INSTANCEOF << 8));
        KEYWORDS.put("throw", THROW);
        KEYWORDS.put("try", TRY);
    }

    private int stringToKeyword(String name) {
        Integer id = KEYWORDS.get(name);
        if (id == null) return EOF;

        this.op = id >> 8;
        return id & 0xff;
    }

    public TokenStream(
            Reader in,
            String sourceName, CodePosition position
    ) {
        this.in = new LineBuffer(in, position);
        this.pushbackToken = EOF;
        this.sourceName = sourceName;
        flags = 0;
        secondToLastPosition = position;
        lastPosition = position;
        lastTokenPosition = position;
    }

    /* return and pop the token from the stream if it matches...
     * otherwise return null
     */
    public boolean matchToken(int toMatch) throws IOException {
        int token = getToken();
        if (token == toMatch) {
            return true;
        }

        // didn't match, push back token
        ungetToken(token);
        return false;
    }

    public void ungetToken(int tt) {
        if (this.pushbackToken != EOF && tt != ERROR) {
            String message = Context.getMessage2("msg.token.replaces.pushback",
                                                 tokenToString(tt), tokenToString(this.pushbackToken));
            throw new RuntimeException(message);
        }
        this.pushbackToken = tt;
        lastPosition = secondToLastPosition;
        lastTokenPosition = tokenPosition;
        tokenno--;
    }

    public int peekToken() throws IOException {
        return peekTokenHelper(getToken());
    }

    private int peekTokenHelper(int token) throws IOException {
        this.pushbackToken = token;
        lastPosition = secondToLastPosition;
        lastTokenPosition = tokenPosition;
        tokenno--;
        return token;
    }

    public int peekTokenSameLine() throws IOException {
        int result;

        flags |= TSF_NEWLINES;          // SCAN_NEWLINES from jsscan.h
        result = peekToken();
        flags &= ~TSF_NEWLINES;         // HIDE_NEWLINES from jsscan.h
        if (this.pushbackToken == EOL) {
            this.pushbackToken = EOF;
        }
        return result;
    }

    private static boolean isAlpha(int c) {
        return ((c >= 'a' && c <= 'z')
                || (c >= 'A' && c <= 'Z'));
    }

    static boolean isDigit(int c) {
        return (c >= '0' && c <= '9');
    }

    static int xDigitToInt(int c) {
        if ('0' <= c && c <= '9') {
            return c - '0';
        }
        if ('a' <= c && c <= 'f') {
            return c - ('a' - 10);
        }
        if ('A' <= c && c <= 'F') {
            return c - ('A' - 10);
        }
        return -1;
    }

    /* As defined in ECMA.  jsscan.c uses C isspace() (which allows
     * \v, I think.)  note that code in in.read() implicitly accepts
     * '\r' == \u000D as well.
     */
    public static boolean isJSSpace(int c) {
        return (c == '\u0020' || c == '\u0009'
                || c == '\u000C' || c == '\u000B'
                || c == '\u00A0'
                || Character.getType((char) c) == Character.SPACE_SEPARATOR);
    }

    private void skipLine() throws IOException {
        // skip to end of line
        int c;
        while ((c = in.read()) != EOF_CHAR && c != '\n') {
        }
        in.unread();
    }

    public void collectCommentsAfter() throws IOException {
        ungetToken(getToken());
    }

    public int getToken() throws IOException {
        lastTokenPosition = tokenPosition;
        int c;
        do {
            c = getTokenHelper();
        }
        while (c == RETRY_TOKEN);

        updatePosition();
        return c;
    }

    private int getTokenHelper() throws IOException {
        int c;
        tokenno++;

        // Check for pushed-back token
        if (this.pushbackToken != EOF) {
            int result = this.pushbackToken;
            this.pushbackToken = EOF;
            return result;
        }

        // Eat whitespace, possibly sensitive to newlines.
        do {
            c = in.read();
            if (c == '\n') {
                flags &= ~TSF_DIRTYLINE;
                if ((flags & TSF_NEWLINES) != 0) {
                    break;
                }
            }
        }
        while (isJSSpace(c) || c == '\n');

        tokenPosition = new CodePosition(in.getLineno(), Math.max(in.getOffset() - 1, 0));
        if (c == EOF_CHAR) {
            return EOF;
        }
        if (c != '-' && c != '\n') {
            flags |= TSF_DIRTYLINE;
        }

        // identifier/keyword/instanceof?
        // watch out for starting with a <backslash>
        boolean identifierStart;
        boolean isUnicodeEscapeStart = false;
        if (c == '\\') {
            c = in.read();
            if (c == 'u') {
                identifierStart = true;
                isUnicodeEscapeStart = true;
                stringBufferTop = 0;
            }
            else {
                identifierStart = false;
                c = '\\';
                in.unread();
            }
        }
        else {
            identifierStart = Character.isJavaIdentifierStart((char) c);
            if (identifierStart) {
                stringBufferTop = 0;
                addToString(c);
            }

            // bruce: special handling of JSNI signatures
            // - it would be nice to handle Unicode escapes in the future
            //
            if (c == '@') {
                stringBufferTop = 0;
                addToString(c);
                return jsniMatchReference();
            }
        }

        if (identifierStart) {
            boolean containsEscape = isUnicodeEscapeStart;
            for (; ; ) {
                if (isUnicodeEscapeStart) {
                    // strictly speaking we should probably push-back
                    // all the bad characters if the <backslash>uXXXX
                    // sequence is malformed. But since there isn't a
                    // correct context(is there?) for a bad Unicode
                    // escape sequence in an identifier, we can report
                    // an error here.
                    int escapeVal = 0;
                    for (int i = 0; i != 4; ++i) {
                        c = in.read();
                        escapeVal = (escapeVal << 4) | xDigitToInt(c);
                        // Next check takes care about c < 0 and bad escape
                        if (escapeVal < 0) {
                            break;
                        }
                    }
                    if (escapeVal < 0) {
                        reportTokenError("msg.invalid.escape", null);
                        return ERROR;
                    }
                    addToString(escapeVal);
                    isUnicodeEscapeStart = false;
                }
                else {
                    c = in.read();
                    if (c == '\\') {
                        c = in.read();
                        if (c == 'u') {
                            isUnicodeEscapeStart = true;
                            containsEscape = true;
                        }
                        else {
                            reportTokenError("msg.illegal.character", null);
                            return ERROR;
                        }
                    }
                    else {
                        if (!Character.isJavaIdentifierPart((char) c)) {
                            break;
                        }
                        addToString(c);
                    }
                }
            }
            in.unread();

            String str = getStringFromBuffer();
            if (!containsEscape && !treatKeywordAsIdentifier) {
                // OPT we shouldn't have to make a string (object!) to
                // check if it's a keyword.

                // Return the corresponding token if it's a keyword
                int result = stringToKeyword(str);
                if (result != EOF) {
                    return result;
                }
            }
            this.string = str;
            return NAME;
        }

        // is it a number?
        if (isDigit(c) || (c == '.' && isDigit(in.peek()))) {

            stringBufferTop = 0;
            int base = 10;

            if (c == '0') {
                c = in.read();
                if (c == 'x' || c == 'X') {
                    base = 16;
                    c = in.read();
                }
                else if (isDigit(c)) {
                    base = 8;
                }
                else {
                    addToString('0');
                }
            }

            if (base == 16) {
                while (0 <= xDigitToInt(c)) {
                    addToString(c);
                    c = in.read();
                }
            }
            else {
                while ('0' <= c && c <= '9') {
                    /*
                     * We permit 08 and 09 as decimal numbers, which
                     * makes our behavior a superset of the ECMA
                     * numeric grammar.  We might not always be so
                     * permissive, so we warn about it.
                     */
                    if (base == 8 && c >= '8') {
                        Object[] errArgs = {c == '8' ? "8" : "9"};
                        reportTokenWarning("msg.bad.octal.literal", errArgs);
                        base = 10;
                    }
                    addToString(c);
                    c = in.read();
                }
            }

            boolean isInteger = true;

            if (base == 10 && (c == '.' || c == 'e' || c == 'E')) {
                isInteger = false;
                if (c == '.') {
                    do {
                        addToString(c);
                        c = in.read();
                    }
                    while (isDigit(c));
                }
                if (c == 'e' || c == 'E') {
                    addToString(c);
                    c = in.read();
                    if (c == '+' || c == '-') {
                        addToString(c);
                        c = in.read();
                    }
                    if (!isDigit(c)) {
                        reportTokenError("msg.missing.exponent", null);
                        return ERROR;
                    }
                    do {
                        addToString(c);
                        c = in.read();
                    }
                    while (isDigit(c));
                }
            }
            in.unread();
            String numString = getStringFromBuffer();

            double dval;
            if (base == 10 && !isInteger) {
                try {
                    // Use Java conversion to number from string...
                    dval = (Double.valueOf(numString)).doubleValue();
                }
                catch (NumberFormatException ex) {
                    Object[] errArgs = {ex.getMessage()};
                    reportTokenError("msg.caught.nfe", errArgs);
                    return ERROR;
                }
            }
            else {
                dval = ScriptRuntime.stringToNumber(numString, 0, base);
            }

            this.number = dval;

            if (isInteger) {
                return NUMBER_INT;
            }

            return NUMBER;
        }

        // is it a string?
        if (c == '"' || c == '\'') {
            // We attempt to accumulate a string the fast way, by
            // building it directly out of the reader.  But if there
            // are any escaped characters in the string, we revert to
            // building it out of a StringBuffer.

            int quoteChar = c;
            int val = 0;
            stringBufferTop = 0;

            c = in.read();
            strLoop:
            while (c != quoteChar) {
                if (c == '\n' || c == EOF_CHAR) {
                    in.unread();
                    reportTokenError("msg.unterminated.string.lit", null);
                    return ERROR;
                }

                if (c == '\\') {
                    // We've hit an escaped character

                    c = in.read();
                    switch (c) {
                        case 'b':
                            c = '\b';
                            break;
                        case 'f':
                            c = '\f';
                            break;
                        case 'n':
                            c = '\n';
                            break;
                        case 'r':
                            c = '\r';
                            break;
                        case 't':
                            c = '\t';
                            break;

                        // \v a late addition to the ECMA spec,
                        // it is not in Java, so use 0xb
                        case 'v':
                            c = 0xb;
                            break;

                        case 'u': {
                            /*
                             * Get 4 hex digits; if the u escape is not
                             * followed by 4 hex digits, use 'u' + the literal
                             * character sequence that follows.
                             */
                            int escapeStart = stringBufferTop;
                            addToString('u');
                            int escapeVal = 0;
                            for (int i = 0; i != 4; ++i) {
                                c = in.read();
                                escapeVal = (escapeVal << 4) | xDigitToInt(c);
                                if (escapeVal < 0) {
                                    continue strLoop;
                                }
                                addToString(c);
                            }
                            // prepare for replace of stored 'u' sequence
                            // by escape value
                            stringBufferTop = escapeStart;
                            c = escapeVal;
                        }
                        break;

                        case 'x': {
                            /* Get 2 hex digits, defaulting to 'x' + literal
                             * sequence, as above.
                             */
                            c = in.read();
                            int escapeVal = xDigitToInt(c);
                            if (escapeVal < 0) {
                                addToString('x');
                                continue strLoop;
                            }
                            else {
                                int c1 = c;
                                c = in.read();
                                escapeVal = (escapeVal << 4) | xDigitToInt(c);
                                if (escapeVal < 0) {
                                    addToString('x');
                                    addToString(c1);
                                    continue strLoop;
                                }
                                else {
                                    // got 2 hex digits
                                    c = escapeVal;
                                }
                            }
                        }
                        break;

                        case '\n':
                            // Remove line terminator
                            c = in.read();
                            continue strLoop;

                        default:
                            if ('0' <= c && c < '8') {
                                val = c - '0';
                                c = in.read();
                                if ('0' <= c && c < '8') {
                                    val = 8 * val + c - '0';
                                    c = in.read();
                                    if ('0' <= c && c < '8' && val <= 037) {
                                        // c is 3rd char of octal sequence only if
                                        // the resulting val <= 0377
                                        val = 8 * val + c - '0';
                                        c = in.read();
                                    }
                                }
                                in.unread();
                                c = val;
                            }
                    }
                }
                addToString(c);
                c = in.read();
            }

            this.string = getStringFromBuffer();
            return STRING;
        }

        switch (c) {
            case '\n':
                return EOL;
            case ';':
                return SEMI;
            case '[':
                return LB;
            case ']':
                return RB;
            case '{':
                return LC;
            case '}':
                return RC;
            case '(':
                return LP;
            case ')':
                return GWT;
            case ',':
                return COMMA;
            case '?':
                return HOOK;
            case ':':
                return COLON;
            case '.':
                return DOT;

            case '|':
                if (in.match('|')) {
                    return OR;
                }
                else if (in.match('=')) {
                    this.op = BITOR;
                    return ASSIGN;
                }
                else {
                    return BITOR;
                }

            case '^':
                if (in.match('=')) {
                    this.op = BITXOR;
                    return ASSIGN;
                }
                else {
                    return BITXOR;
                }

            case '&':
                if (in.match('&')) {
                    return AND;
                }
                else if (in.match('=')) {
                    this.op = BITAND;
                    return ASSIGN;
                }
                else {
                    return BITAND;
                }

            case '=':
                if (in.match('=')) {
                    if (in.match('=')) {
                        this.op = SHEQ;
                    }
                    else {
                        this.op = EQ;
                    }
                    return EQOP;
                }
                else {
                    this.op = NOP;
                    return ASSIGN;
                }

            case '!':
                if (in.match('=')) {
                    if (in.match('=')) {
                        this.op = SHNE;
                    }
                    else {
                        this.op = NE;
                    }
                    return EQOP;
                }
                else {
                    this.op = NOT;
                    return UNARYOP;
                }

            case '<':
                /* NB:treat HTML begin-comment as comment-till-eol */
                if (in.match('!')) {
                    if (in.match('-')) {
                        if (in.match('-')) {
                            skipLine();
                            return RETRY_TOKEN;  // in place of 'goto retry'
                        }
                        in.unread();
                    }
                    in.unread();
                }
                if (in.match('<')) {
                    if (in.match('=')) {
                        this.op = LSH;
                        return ASSIGN;
                    }
                    else {
                        this.op = LSH;
                        return SHOP;
                    }
                }
                else {
                    if (in.match('=')) {
                        this.op = LE;
                        return RELOP;
                    }
                    else {
                        this.op = LT;
                        return RELOP;
                    }
                }

            case '>':
                if (in.match('>')) {
                    if (in.match('>')) {
                        if (in.match('=')) {
                            this.op = URSH;
                            return ASSIGN;
                        }
                        else {
                            this.op = URSH;
                            return SHOP;
                        }
                    }
                    else {
                        if (in.match('=')) {
                            this.op = RSH;
                            return ASSIGN;
                        }
                        else {
                            this.op = RSH;
                            return SHOP;
                        }
                    }
                }
                else {
                    if (in.match('=')) {
                        this.op = GE;
                        return RELOP;
                    }
                    else {
                        this.op = GT;
                        return RELOP;
                    }
                }

            case '*':
                if (in.match('=')) {
                    this.op = MUL;
                    return ASSIGN;
                }
                else {
                    return MUL;
                }

            case '/':
                // is it a // comment?
                if (in.match('/')) {
                    stringBufferTop = 0;
                    while ((c = in.read()) != -1 && c != '\n') {
                        addToString(c);
                    }
                    addCommentToQueue(new Comment(getStringFromBuffer(), false));
                    return RETRY_TOKEN;
                }
                if (in.match('*')) {
                    stringBufferTop = 0;
                    while ((c = in.read()) != -1 &&
                           !(c == '*' && in.match('/'))) {
                        addToString(c);
                    }
                    if (c == EOF_CHAR) {
                        reportTokenError("msg.unterminated.comment", null);
                        return ERROR;
                    }
                    addCommentToQueue(new Comment(getStringFromBuffer(), true));
                    return RETRY_TOKEN;  // `goto retry'
                }

                // is it a regexp?
                if ((flags & TSF_REGEXP) != 0) {
                    stringBufferTop = 0;
                    while ((c = in.read()) != '/') {
                        if (c == '\n' || c == EOF_CHAR) {
                            in.unread();
                            reportTokenError("msg.unterminated.re.lit", null);
                            return ERROR;
                        }
                        if (c == '\\') {
                            addToString(c);
                            c = in.read();
                        }

                        addToString(c);
                    }
                    int reEnd = stringBufferTop;

                    while (true) {
                        if (in.match('g')) {
                            addToString('g');
                        }
                        else if (in.match('i')) {
                            addToString('i');
                        }
                        else if (in.match('m')) {
                            addToString('m');
                        }
                        else {
                            break;
                        }
                    }

                    if (isAlpha(in.peek())) {
                        reportTokenError("msg.invalid.re.flag", null);
                        return ERROR;
                    }

                    this.string = new String(stringBuffer, 0, reEnd);
                    this.regExpFlags = new String(stringBuffer, reEnd,
                                                  stringBufferTop - reEnd);
                    return REGEXP;
                }


                if (in.match('=')) {
                    this.op = DIV;
                    return ASSIGN;
                }
                else {
                    return DIV;
                }

            case '%':
                this.op = MOD;
                if (in.match('=')) {
                    return ASSIGN;
                }
                else {
                    return MOD;
                }

            case '~':
                this.op = BITNOT;
                return UNARYOP;

            case '+':
                if (in.match('=')) {
                    this.op = ADD;
                    return ASSIGN;
                }
                else if (in.match('+')) {
                    return INC;
                }
                else {
                    return ADD;
                }

            case '-':
                if (in.match('=')) {
                    this.op = SUB;
                    c = ASSIGN;
                }
                else if (in.match('-')) {
                    if (0 == (flags & TSF_DIRTYLINE)) {
                        // treat HTML end-comment after possible whitespace
                        // after line start as comment-utill-eol
                        if (in.match('>')) {
                            skipLine();
                            return RETRY_TOKEN;
                        }
                    }
                    c = DEC;
                }
                else {
                    c = SUB;
                }
                flags |= TSF_DIRTYLINE;
                return c;

            default:
                reportTokenError("msg.illegal.character", null);
                return ERROR;
        }
    }

    private void skipWhitespace() throws IOException {
        int tmp;
        do {
            tmp = in.read();
        }
        while (isJSSpace(tmp) || tmp == '\n');
        // Reposition back to first non whitespace char.
        in.unread();
    }

    private int jsniMatchReference() throws IOException {

        // First, read the type name whose member is being accessed.
        if (!jsniMatchQualifiedTypeName('.', ':')) {
            return ERROR;
        }

        // Now we must the second colon.
        //
        int c = in.read();
        if (c != ':') {
            in.unread();
            reportTokenError("msg.jsni.expected.char", new String[] {":"});
            return ERROR;
        }
        addToString(c);

        // Skip whitespace starting after ::.
        skipWhitespace();

        // Finish by reading the field or method signature.
        if (!jsniMatchMethodSignatureOrFieldName()) {
            return ERROR;
        }

        this.string = new String(stringBuffer, 0, stringBufferTop);
        return NAME;
    }

    private boolean jsniMatchParamListSignature() throws IOException {
        // Assume the opening '(' has already been read.
        // Read param type signatures until we see a closing ')'.

        skipWhitespace();

        // First check for the special case of * as the parameter list, indicating
        // a wildcard
        if (in.peek() == '*') {
            addToString(in.read());
            if (in.peek() != ')') {
                reportTokenError("msg.jsni.expected.char", new String[] {")"});
            }
            addToString(in.read());
            return true;
        }

        // Otherwise, loop through reading one param type at a time
        do {
            // Skip whitespace between parameters.
            skipWhitespace();

            int c = in.read();

            if (c == ')') {
                // Finished successfully.
                //
                addToString(c);
                return true;
            }

            in.unread();
        }
        while (jsniMatchParamTypeSignature());

        // If we made it here, we can assume that there was an invalid type
        // signature that was already reported and that the offending char
        // was already unread.
        //
        return false;
    }

    private boolean jsniMatchParamTypeSignature() throws IOException {
        int c = in.read();
        switch (c) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
            case 'J':
            case 'F':
            case 'D':
                // Primitive type id.
                addToString(c);
                return true;
            case 'L':
                // Class/Interface type prefix.
                addToString(c);
                return jsniMatchQualifiedTypeName('/', ';');
            case '[':
                // Array type prefix.
                addToString(c);
                return jsniMatchParamArrayTypeSignature();
            default:
                in.unread();
                reportTokenError("msg.jsni.expected.param.type", null);
                return false;
        }
    }

    private boolean jsniMatchParamArrayTypeSignature() throws IOException {
        // Assume the leading '[' has already been read.
        // What follows must be another param type signature.
        //
        return jsniMatchParamTypeSignature();
    }

    private boolean jsniMatchMethodSignatureOrFieldName() throws IOException {
        int c = in.read();


        // We must see an ident start here.
        //
        if (!Character.isJavaIdentifierStart((char) c)) {
            in.unread();
            reportTokenError("msg.jsni.expected.identifier", null);
            return false;
        }

        addToString(c);

        for (; ; ) {
            c = in.read();
            if (Character.isJavaIdentifierPart((char) c)) {
                addToString(c);
            }
            else if (c == '(') {
                // This means we're starting a JSNI method signature.
                //
                addToString(c);
                if (jsniMatchParamListSignature()) {
                    // Finished a method signature with success.
                    // Assume the callee unread the last char.
                    //
                    return true;
                }
                else {
                    // Assume the callee reported the error and unread the last char.
                    //
                    return false;
                }
            }
            else {
                // We don't know this char, so it finishes the token.
                //
                in.unread();
                return true;
            }
        }
    }

    /**
     * This method is called to match the fully-qualified type name that
     * should appear after the '@' in a JSNI reference.
     *
     * @param sepChar the character that will separate the Java idents
     *                (either a '.' or '/')
     * @param endChar the character that indicates the end of the
     */
    private boolean jsniMatchQualifiedTypeName(char sepChar, char endChar)
            throws IOException {
        int c = in.read();

        // Whether nested or not, we must see an ident start here.
        //
        if (!Character.isJavaIdentifierStart((char) c)) {
            in.unread();
            reportTokenError("msg.jsni.expected.identifier", null);
            return false;
        }

        // Now actually add the first ident char.
        //
        addToString(c);

        // And append any other ident chars.
        //
        for (; ; ) {
            c = in.read();
            if (Character.isJavaIdentifierPart((char) c)) {
                addToString(c);
            }
            else {
                break;
            }
        }

        // Arrray-type reference
        while (c == '[') {
            if (']' == in.peek()) {
                addToString('[');
                addToString(in.read());
                c = in.read();
            }
            else {
                break;
            }
        }

        // We have a non-ident char to classify.
        //
        if (c == sepChar) {
            addToString(c);
            if (jsniMatchQualifiedTypeName(sepChar, endChar)) {
                // We consumed up to the endChar, so we finished with total success.
                //
                return true;
            }
            else {
                // Assume that the nested call reported the syntax error and
                // unread the last character.
                //
                return false;
            }
        }
        else if (c == endChar) {
            // Matched everything up to the specified end char.
            //
            addToString(c);
            return true;
        }
        else {
            // This is an unknown char that finishes the token.
            //
            in.unread();
            return true;
        }
    }

    private String getStringFromBuffer() {
        return new String(stringBuffer, 0, stringBufferTop);
    }

    private void addToString(int c) {
        if (stringBufferTop == stringBuffer.length) {
            char[] tmp = new char[stringBuffer.length * 2];
            System.arraycopy(stringBuffer, 0, tmp, 0, stringBufferTop);
            stringBuffer = tmp;
        }
        stringBuffer[stringBufferTop++] = (char) c;
    }

    /**
     * Positions hold offset of an corresponding token's end.
     * So lastPosition holds an offset of char that is next to last token.
     * <p>
     * Use secondToLastPosition for error reporting outside of TokenStream, because
     * usually we want to report beginning of erroneous token,
     * which is end of second to last read token.
     */
    public void reportSyntaxError(String messageProperty, Object[] args) {
        String message = Context.getMessage(messageProperty, args);
        Context.reportError(message, secondToLastPosition, lastPosition);
    }

    /**
     * Token errors are reported before tokes is read,
     * so use lastPosition for reporting.
     *
     * @see #reportSyntaxError
     */
    private void reportTokenError(String messageProperty, Object[] args) {
        String message = Context.getMessage(messageProperty, args);
        Context.reportError(message, lastPosition, new CodePosition(getLineno(), getOffset()));
    }

    private void reportTokenWarning(String messageProperty, Object[] args) {
        String message = Context.getMessage(messageProperty, args);
        Context.reportWarning(message, lastPosition, new CodePosition(getLineno(), getOffset()));
    }

    /**
     * Updates last two known positions (for error reporting).
     */
    private void updatePosition() {
        CodePosition currentPosition = new CodePosition(getLineno(), getOffset());
        if (currentPosition.compareTo(lastPosition) > 0) {
            secondToLastPosition = lastPosition;
            lastPosition = currentPosition;
        }
    }

    private void addCommentToQueue(Comment comment) {
        if (headComment == null) {
            headComment = comment;
            lastComment = comment;
        }
        else {
            lastComment.setNext(comment);
            lastComment = comment;
        }
    }

    public String getSourceName() {
        return sourceName;
    }

    public int getLineno() {
        return in.getLineno();
    }

    public int getOp() {
        return op;
    }

    public String getString() {
        return string;
    }

    public double getNumber() {
        return number;
    }

    public String getLine() {
        return in.getLine();
    }

    public int getOffset() {
        return in.getOffset();
    }

    public int getTokenno() {
        return tokenno;
    }

    public boolean eof() {
        return in.eof();
    }

    public Comment getHeadComment() {
        return headComment;
    }

    public void releaseComments() {
        headComment = null;
        lastComment = null;
    }

    // instance variables
    private LineBuffer in;


    /* for TSF_REGEXP, etc.
     * should this be manipulated by gettor/settor functions?
     * should it be passed to getToken();
     */
    int flags;
    String regExpFlags;

    private String sourceName;
    private int pushbackToken;
    private int tokenno;

    CodePosition secondToLastPosition;
    CodePosition lastPosition;
    CodePosition tokenPosition;
    CodePosition lastTokenPosition;

    private Comment headComment;

    private Comment lastComment;

    private int op;
    public boolean treatKeywordAsIdentifier;

    // Set this to an initial non-null value so that the Parser has
    // something to retrieve even if an error has occurred and no
    // string is found.  Fosters one class of error, but saves lots of
    // code.
    private String string = "";
    private double number;

    private char[] stringBuffer = new char[128];
    private int stringBufferTop;
}
