/*
 * Copyright 2010-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.jet.codegen.generated;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestSuite;

import java.io.File;
import java.util.regex.Pattern;
import org.jetbrains.jet.JetTestUtils;
import org.jetbrains.jet.test.InnerTestClasses;
import org.jetbrains.jet.test.TestMetadata;

import org.jetbrains.jet.codegen.generated.AbstractBlackBoxCodegenTest;

/** This class is generated by {@link org.jetbrains.jet.generators.tests.GenerateTests}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("compiler/testData/codegen/boxWithStdlib")
@InnerTestClasses({BlackBoxWithStdlibCodegenTestGenerated.Casts.class, BlackBoxWithStdlibCodegenTestGenerated.DataClasses.class, BlackBoxWithStdlibCodegenTestGenerated.FullJdk.class, BlackBoxWithStdlibCodegenTestGenerated.JdkAnnotations.class, BlackBoxWithStdlibCodegenTestGenerated.Ranges.class, BlackBoxWithStdlibCodegenTestGenerated.Regressions.class, BlackBoxWithStdlibCodegenTestGenerated.Strings.class})
public class BlackBoxWithStdlibCodegenTestGenerated extends AbstractBlackBoxCodegenTest {
    public void testAllFilesPresentInBoxWithStdlib() throws Exception {
        JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib"), Pattern.compile("^(.+)\\.kt$"), true);
    }
    
    @TestMetadata("compiler/testData/codegen/boxWithStdlib/casts")
    public static class Casts extends AbstractBlackBoxCodegenTest {
        public void testAllFilesPresentInCasts() throws Exception {
            JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/casts"), Pattern.compile("^(.+)\\.kt$"), true);
        }
        
        @TestMetadata("asWithGeneric.kt")
        public void testAsWithGeneric() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/casts/asWithGeneric.kt");
        }
        
    }
    
    @TestMetadata("compiler/testData/codegen/boxWithStdlib/dataClasses")
    @InnerTestClasses({DataClasses.Copy.class, DataClasses.Equals.class, DataClasses.Hashcode.class, DataClasses.Tostring.class})
    public static class DataClasses extends AbstractBlackBoxCodegenTest {
        public void testAllFilesPresentInDataClasses() throws Exception {
            JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/dataClasses"), Pattern.compile("^(.+)\\.kt$"), true);
        }
        
        @TestMetadata("arrayParams.kt")
        public void testArrayParams() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/arrayParams.kt");
        }
        
        @TestMetadata("changingVarParam.kt")
        public void testChangingVarParam() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/changingVarParam.kt");
        }
        
        @TestMetadata("genericParam.kt")
        public void testGenericParam() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/genericParam.kt");
        }
        
        @TestMetadata("mixedParams.kt")
        public void testMixedParams() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/mixedParams.kt");
        }
        
        @TestMetadata("multiDeclaration.kt")
        public void testMultiDeclaration() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/multiDeclaration.kt");
        }
        
        @TestMetadata("multiDeclarationFor.kt")
        public void testMultiDeclarationFor() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/multiDeclarationFor.kt");
        }
        
        @TestMetadata("overriddenProperty.kt")
        public void testOverriddenProperty() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/overriddenProperty.kt");
        }
        
        @TestMetadata("twoValParams.kt")
        public void testTwoValParams() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/twoValParams.kt");
        }
        
        @TestMetadata("twoVarParams.kt")
        public void testTwoVarParams() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/twoVarParams.kt");
        }
        
        @TestMetadata("unitComponent.kt")
        public void testUnitComponent() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/unitComponent.kt");
        }
        
        @TestMetadata("compiler/testData/codegen/boxWithStdlib/dataClasses/copy")
        public static class Copy extends AbstractBlackBoxCodegenTest {
            public void testAllFilesPresentInCopy() throws Exception {
                JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/dataClasses/copy"), Pattern.compile("^(.+)\\.kt$"), true);
            }
            
            @TestMetadata("constructorWithDefaultParam.kt")
            public void testConstructorWithDefaultParam() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/copy/constructorWithDefaultParam.kt");
            }
            
            @TestMetadata("copyInNestedDataClass.kt")
            public void testCopyInNestedDataClass() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/copy/copyInNestedDataClass.kt");
            }
            
            @TestMetadata("copyInObjectNestedDataClass.kt")
            public void testCopyInObjectNestedDataClass() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/copy/copyInObjectNestedDataClass.kt");
            }
            
            @TestMetadata("kt3033.kt")
            public void testKt3033() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/copy/kt3033.kt");
            }
            
            @TestMetadata("paramWithoutProperty.kt")
            public void testParamWithoutProperty() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/copy/paramWithoutProperty.kt");
            }
            
            @TestMetadata("valInConstructorParams.kt")
            public void testValInConstructorParams() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/copy/valInConstructorParams.kt");
            }
            
            @TestMetadata("varInConstructorParams.kt")
            public void testVarInConstructorParams() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/copy/varInConstructorParams.kt");
            }
            
            @TestMetadata("withGenericParameter.kt")
            public void testWithGenericParameter() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/copy/withGenericParameter.kt");
            }
            
        }
        
        @TestMetadata("compiler/testData/codegen/boxWithStdlib/dataClasses/equals")
        public static class Equals extends AbstractBlackBoxCodegenTest {
            public void testAllFilesPresentInEquals() throws Exception {
                JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/dataClasses/equals"), Pattern.compile("^(.+)\\.kt$"), true);
            }
            
            @TestMetadata("alreadyDeclared.kt")
            public void testAlreadyDeclared() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/equals/alreadyDeclared.kt");
            }
            
            @TestMetadata("alreadyDeclaredWrongSignature.kt")
            public void testAlreadyDeclaredWrongSignature() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/equals/alreadyDeclaredWrongSignature.kt");
            }
            
            @TestMetadata("genericarray.kt")
            public void testGenericarray() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/equals/genericarray.kt");
            }
            
            @TestMetadata("instanceof.kt")
            public void testInstanceof() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/equals/instanceof.kt");
            }
            
            @TestMetadata("intarray.kt")
            public void testIntarray() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/equals/intarray.kt");
            }
            
            @TestMetadata("nullother.kt")
            public void testNullother() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/equals/nullother.kt");
            }
            
            @TestMetadata("sameinstance.kt")
            public void testSameinstance() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/equals/sameinstance.kt");
            }
            
        }
        
        @TestMetadata("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode")
        public static class Hashcode extends AbstractBlackBoxCodegenTest {
            public void testAllFilesPresentInHashcode() throws Exception {
                JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode"), Pattern.compile("^(.+)\\.kt$"), true);
            }
            
            @TestMetadata("alreadyDeclared.kt")
            public void testAlreadyDeclared() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/alreadyDeclared.kt");
            }
            
            @TestMetadata("alreadyDeclaredWrongSignature.kt")
            public void testAlreadyDeclaredWrongSignature() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/alreadyDeclaredWrongSignature.kt");
            }
            
            @TestMetadata("array.kt")
            public void testArray() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/array.kt");
            }
            
            @TestMetadata("boolean.kt")
            public void testBoolean() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/boolean.kt");
            }
            
            @TestMetadata("byte.kt")
            public void testByte() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/byte.kt");
            }
            
            @TestMetadata("char.kt")
            public void testChar() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/char.kt");
            }
            
            @TestMetadata("double.kt")
            public void testDouble() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/double.kt");
            }
            
            @TestMetadata("float.kt")
            public void testFloat() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/float.kt");
            }
            
            @TestMetadata("genericNull.kt")
            public void testGenericNull() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/genericNull.kt");
            }
            
            @TestMetadata("int.kt")
            public void testInt() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/int.kt");
            }
            
            @TestMetadata("long.kt")
            public void testLong() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/long.kt");
            }
            
            @TestMetadata("null.kt")
            public void testNull() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/null.kt");
            }
            
            @TestMetadata("short.kt")
            public void testShort() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/hashcode/short.kt");
            }
            
        }
        
        @TestMetadata("compiler/testData/codegen/boxWithStdlib/dataClasses/tostring")
        public static class Tostring extends AbstractBlackBoxCodegenTest {
            public void testAllFilesPresentInTostring() throws Exception {
                JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/dataClasses/tostring"), Pattern.compile("^(.+)\\.kt$"), true);
            }
            
            @TestMetadata("alreadyDeclared.kt")
            public void testAlreadyDeclared() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/tostring/alreadyDeclared.kt");
            }
            
            @TestMetadata("alreadyDeclaredWrongSignature.kt")
            public void testAlreadyDeclaredWrongSignature() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/tostring/alreadyDeclaredWrongSignature.kt");
            }
            
            @TestMetadata("arrayParams.kt")
            public void testArrayParams() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/tostring/arrayParams.kt");
            }
            
            @TestMetadata("changingVarParam.kt")
            public void testChangingVarParam() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/tostring/changingVarParam.kt");
            }
            
            @TestMetadata("genericParam.kt")
            public void testGenericParam() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/tostring/genericParam.kt");
            }
            
            @TestMetadata("mixedParams.kt")
            public void testMixedParams() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/tostring/mixedParams.kt");
            }
            
            @TestMetadata("overriddenProperty.kt")
            public void testOverriddenProperty() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/tostring/overriddenProperty.kt");
            }
            
            @TestMetadata("unitComponent.kt")
            public void testUnitComponent() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/dataClasses/tostring/unitComponent.kt");
            }
            
        }
        
        public static Test innerSuite() {
            TestSuite suite = new TestSuite("DataClasses");
            suite.addTestSuite(DataClasses.class);
            suite.addTestSuite(Copy.class);
            suite.addTestSuite(Equals.class);
            suite.addTestSuite(Hashcode.class);
            suite.addTestSuite(Tostring.class);
            return suite;
        }
    }
    
    @TestMetadata("compiler/testData/codegen/boxWithStdlib/fullJdk")
    public static class FullJdk extends AbstractBlackBoxCodegenTest {
        public void testAllFilesPresentInFullJdk() throws Exception {
            JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/fullJdk"), Pattern.compile("^(.+)\\.kt$"), true);
        }
        
        @TestMetadata("ifInWhile.kt")
        public void testIfInWhile() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/fullJdk/ifInWhile.kt");
        }
        
        @TestMetadata("intCountDownLatchExtension.kt")
        public void testIntCountDownLatchExtension() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/fullJdk/intCountDownLatchExtension.kt");
        }
        
        @TestMetadata("kt2423.kt")
        public void testKt2423() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/fullJdk/kt2423.kt");
        }
        
        @TestMetadata("kt2509.kt")
        public void testKt2509() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/fullJdk/kt2509.kt");
        }
        
        @TestMetadata("kt434.kt")
        public void testKt434() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/fullJdk/kt434.kt");
        }
        
        @TestMetadata("sync.kt")
        public void testSync() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/fullJdk/sync.kt");
        }
        
    }
    
    @TestMetadata("compiler/testData/codegen/boxWithStdlib/jdkAnnotations")
    public static class JdkAnnotations extends AbstractBlackBoxCodegenTest {
        public void testAllFilesPresentInJdkAnnotations() throws Exception {
            JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/jdkAnnotations"), Pattern.compile("^(.+)\\.kt$"), true);
        }
        
        @TestMetadata("arrayList.kt")
        public void testArrayList() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/jdkAnnotations/arrayList.kt");
        }
        
        @TestMetadata("hashMap.kt")
        public void testHashMap() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/jdkAnnotations/hashMap.kt");
        }
        
        @TestMetadata("iteratingOverHashMap.kt")
        public void testIteratingOverHashMap() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/jdkAnnotations/iteratingOverHashMap.kt");
        }
        
        @TestMetadata("kt1397.kt")
        public void testKt1397() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/jdkAnnotations/kt1397.kt");
        }
        
    }
    
    @TestMetadata("compiler/testData/codegen/boxWithStdlib/ranges")
    @InnerTestClasses({Ranges.Expression.class, Ranges.Literal.class})
    public static class Ranges extends AbstractBlackBoxCodegenTest {
        public void testAllFilesPresentInRanges() throws Exception {
            JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/ranges"), Pattern.compile("^(.+)\\.kt$"), true);
        }
        
        @TestMetadata("multiAssignmentIterationOverIntRange.kt")
        public void testMultiAssignmentIterationOverIntRange() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/multiAssignmentIterationOverIntRange.kt");
        }
        
        @TestMetadata("compiler/testData/codegen/boxWithStdlib/ranges/expression")
        public static class Expression extends AbstractBlackBoxCodegenTest {
            public void testAllFilesPresentInExpression() throws Exception {
                JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/ranges/expression"), Pattern.compile("^(.+)\\.kt$"), true);
            }
            
            @TestMetadata("emptyDownto.kt")
            public void testEmptyDownto() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/emptyDownto.kt");
            }
            
            @TestMetadata("emptyRange.kt")
            public void testEmptyRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/emptyRange.kt");
            }
            
            @TestMetadata("inexactSteppedDownTo.kt")
            public void testInexactSteppedDownTo() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/inexactSteppedDownTo.kt");
            }
            
            @TestMetadata("inexactSteppedRange.kt")
            public void testInexactSteppedRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/inexactSteppedRange.kt");
            }
            
            @TestMetadata("infiniteSteps.kt")
            public void testInfiniteSteps() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/infiniteSteps.kt");
            }
            
            @TestMetadata("nanEnds.kt")
            public void testNanEnds() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/nanEnds.kt");
            }
            
            @TestMetadata("oneElementDownTo.kt")
            public void testOneElementDownTo() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/oneElementDownTo.kt");
            }
            
            @TestMetadata("oneElementRange.kt")
            public void testOneElementRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/oneElementRange.kt");
            }
            
            @TestMetadata("reversedBackSequence.kt")
            public void testReversedBackSequence() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/reversedBackSequence.kt");
            }
            
            @TestMetadata("reversedEmptyBackSequence.kt")
            public void testReversedEmptyBackSequence() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/reversedEmptyBackSequence.kt");
            }
            
            @TestMetadata("reversedEmptyRange.kt")
            public void testReversedEmptyRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/reversedEmptyRange.kt");
            }
            
            @TestMetadata("reversedInexactSteppedDownTo.kt")
            public void testReversedInexactSteppedDownTo() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/reversedInexactSteppedDownTo.kt");
            }
            
            @TestMetadata("reversedRange.kt")
            public void testReversedRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/reversedRange.kt");
            }
            
            @TestMetadata("reversedSimpleSteppedRange.kt")
            public void testReversedSimpleSteppedRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/reversedSimpleSteppedRange.kt");
            }
            
            @TestMetadata("simpleDownTo.kt")
            public void testSimpleDownTo() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/simpleDownTo.kt");
            }
            
            @TestMetadata("simpleRange.kt")
            public void testSimpleRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/simpleRange.kt");
            }
            
            @TestMetadata("simpleRangeWithNonConstantEnds.kt")
            public void testSimpleRangeWithNonConstantEnds() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/simpleRangeWithNonConstantEnds.kt");
            }
            
            @TestMetadata("simpleSteppedDownTo.kt")
            public void testSimpleSteppedDownTo() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/simpleSteppedDownTo.kt");
            }
            
            @TestMetadata("simpleSteppedRange.kt")
            public void testSimpleSteppedRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/expression/simpleSteppedRange.kt");
            }
            
        }
        
        @TestMetadata("compiler/testData/codegen/boxWithStdlib/ranges/literal")
        public static class Literal extends AbstractBlackBoxCodegenTest {
            public void testAllFilesPresentInLiteral() throws Exception {
                JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/ranges/literal"), Pattern.compile("^(.+)\\.kt$"), true);
            }
            
            @TestMetadata("emptyDownto.kt")
            public void testEmptyDownto() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/emptyDownto.kt");
            }
            
            @TestMetadata("emptyRange.kt")
            public void testEmptyRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/emptyRange.kt");
            }
            
            @TestMetadata("inexactSteppedDownTo.kt")
            public void testInexactSteppedDownTo() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/inexactSteppedDownTo.kt");
            }
            
            @TestMetadata("inexactSteppedRange.kt")
            public void testInexactSteppedRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/inexactSteppedRange.kt");
            }
            
            @TestMetadata("infiniteSteps.kt")
            public void testInfiniteSteps() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/infiniteSteps.kt");
            }
            
            @TestMetadata("nanEnds.kt")
            public void testNanEnds() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/nanEnds.kt");
            }
            
            @TestMetadata("oneElementDownTo.kt")
            public void testOneElementDownTo() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/oneElementDownTo.kt");
            }
            
            @TestMetadata("oneElementRange.kt")
            public void testOneElementRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/oneElementRange.kt");
            }
            
            @TestMetadata("reversedBackSequence.kt")
            public void testReversedBackSequence() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/reversedBackSequence.kt");
            }
            
            @TestMetadata("reversedEmptyBackSequence.kt")
            public void testReversedEmptyBackSequence() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/reversedEmptyBackSequence.kt");
            }
            
            @TestMetadata("reversedEmptyRange.kt")
            public void testReversedEmptyRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/reversedEmptyRange.kt");
            }
            
            @TestMetadata("reversedInexactSteppedDownTo.kt")
            public void testReversedInexactSteppedDownTo() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/reversedInexactSteppedDownTo.kt");
            }
            
            @TestMetadata("reversedRange.kt")
            public void testReversedRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/reversedRange.kt");
            }
            
            @TestMetadata("reversedSimpleSteppedRange.kt")
            public void testReversedSimpleSteppedRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/reversedSimpleSteppedRange.kt");
            }
            
            @TestMetadata("simpleDownTo.kt")
            public void testSimpleDownTo() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/simpleDownTo.kt");
            }
            
            @TestMetadata("simpleRange.kt")
            public void testSimpleRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/simpleRange.kt");
            }
            
            @TestMetadata("simpleRangeWithNonConstantEnds.kt")
            public void testSimpleRangeWithNonConstantEnds() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/simpleRangeWithNonConstantEnds.kt");
            }
            
            @TestMetadata("simpleSteppedDownTo.kt")
            public void testSimpleSteppedDownTo() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/simpleSteppedDownTo.kt");
            }
            
            @TestMetadata("simpleSteppedRange.kt")
            public void testSimpleSteppedRange() throws Exception {
                doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/ranges/literal/simpleSteppedRange.kt");
            }
            
        }
        
        public static Test innerSuite() {
            TestSuite suite = new TestSuite("Ranges");
            suite.addTestSuite(Ranges.class);
            suite.addTestSuite(Expression.class);
            suite.addTestSuite(Literal.class);
            return suite;
        }
    }
    
    @TestMetadata("compiler/testData/codegen/boxWithStdlib/regressions")
    public static class Regressions extends AbstractBlackBoxCodegenTest {
        public void testAllFilesPresentInRegressions() throws Exception {
            JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/regressions"), Pattern.compile("^(.+)\\.kt$"), true);
        }
        
        @TestMetadata("collections.kt")
        public void testCollections() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/collections.kt");
        }
        
        @TestMetadata("generic.kt")
        public void testGeneric() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/generic.kt");
        }
        
        @TestMetadata("kt1406.kt")
        public void testKt1406() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt1406.kt");
        }
        
        @TestMetadata("kt1568.kt")
        public void testKt1568() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt1568.kt");
        }
        
        @TestMetadata("kt1733.kt")
        public void testKt1733() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt1733.kt");
        }
        
        @TestMetadata("kt1770.kt")
        public void testKt1770() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt1770.kt");
        }
        
        @TestMetadata("kt1779.kt")
        public void testKt1779() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt1779.kt");
        }
        
        @TestMetadata("kt1800.kt")
        public void testKt1800() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt1800.kt");
        }
        
        @TestMetadata("kt1932.kt")
        public void testKt1932() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt1932.kt");
        }
        
        @TestMetadata("kt2017.kt")
        public void testKt2017() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt2017.kt");
        }
        
        @TestMetadata("kt2210.kt")
        public void testKt2210() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt2210.kt");
        }
        
        @TestMetadata("kt2246.kt")
        public void testKt2246() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt2246.kt");
        }
        
        @TestMetadata("kt2318.kt")
        public void testKt2318() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt2318.kt");
        }
        
        @TestMetadata("kt2593.kt")
        public void testKt2593() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt2593.kt");
        }
        
        @TestMetadata("kt274.kt")
        public void testKt274() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt274.kt");
        }
        
        @TestMetadata("kt3046.kt")
        public void testKt3046() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt3046.kt");
        }
        
        @TestMetadata("kt344.kt")
        public void testKt344() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt344.kt");
        }
        
        @TestMetadata("kt528.kt")
        public void testKt528() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt528.kt");
        }
        
        @TestMetadata("kt529.kt")
        public void testKt529() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt529.kt");
        }
        
        @TestMetadata("kt533.kt")
        public void testKt533() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt533.kt");
        }
        
        @TestMetadata("kt715.kt")
        public void testKt715() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt715.kt");
        }
        
        @TestMetadata("kt789.kt")
        public void testKt789() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt789.kt");
        }
        
        @TestMetadata("kt864.kt")
        public void testKt864() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/kt864.kt");
        }
        
        @TestMetadata("typeCastException.kt")
        public void testTypeCastException() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/regressions/typeCastException.kt");
        }
        
    }
    
    @TestMetadata("compiler/testData/codegen/boxWithStdlib/strings")
    public static class Strings extends AbstractBlackBoxCodegenTest {
        public void testAllFilesPresentInStrings() throws Exception {
            JetTestUtils.assertAllTestsPresentByMetadata(this.getClass(), "org.jetbrains.jet.generators.tests.GenerateTests", new File("compiler/testData/codegen/boxWithStdlib/strings"), Pattern.compile("^(.+)\\.kt$"), true);
        }
        
        @TestMetadata("forInString.kt")
        public void testForInString() throws Exception {
            doTestWithStdlib("compiler/testData/codegen/boxWithStdlib/strings/forInString.kt");
        }
        
    }
    
    public static Test suite() {
        TestSuite suite = new TestSuite("BlackBoxWithStdlibCodegenTestGenerated");
        suite.addTestSuite(BlackBoxWithStdlibCodegenTestGenerated.class);
        suite.addTestSuite(Casts.class);
        suite.addTest(DataClasses.innerSuite());
        suite.addTestSuite(FullJdk.class);
        suite.addTestSuite(JdkAnnotations.class);
        suite.addTest(Ranges.innerSuite());
        suite.addTestSuite(Regressions.class);
        suite.addTestSuite(Strings.class);
        return suite;
    }
}
