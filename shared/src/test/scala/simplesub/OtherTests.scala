package simplesub

import org.scalatest.funsuite.AnyFunSuite

@SuppressWarnings(Array("org.wartremover.warts.Equals"))
class OtherTests extends AnyFunSuite {
  /*
    test("canonicalization produces LCD") {

      val typer = new Typer(false) with TypeSimplifier
      import typer.{assert => _, _}
      val tv0, tv1, tv3 = freshVar(0)

      // {f: {B: int, f: 'a}} as 'a  –  cycle length 2
      val st0 = Record("f" -> Record("f" -> tv0 :: "B" -> IntType :: Nil) :: Nil)
      tv0.lowerBounds ::= st0

      // {f: {B: int, f: {A: int, f: 'a}}} as 'a  –  cycle length 3
      val st1 = Record("f" -> Record("f" -> Record("f" -> tv1 :: "A" -> IntType :: Nil) :: "B" -> IntType :: Nil) :: Nil)
      tv1.lowerBounds ::= st1
      tv3.lowerBounds = tv0 :: tv1 :: Nil

      // println(tv3.showBounds)

      val ct = canonicalizeType(tv3)
      val sct = simplifyType(ct)
      val csct = coalesceCompactType(sct).show

      assert(csct == "{f: {B: int, f: {f: {f: {f: {f: 'a}}}}}} as 'a") // cycle length 6

    }
   */

  /*
  test("hello world") {
    val typer = new Typer(false) with TypeSimplifier
    import typer.{assert => _, _}
    val tv0, tv1, tv2, tv3 = freshVar(0)

    tv3.lowerBounds = tv0 :: tv1 :: Nil

    val f = Function(tv1, tv1)
    tv2.upperBounds = tv0 :: f :: Nil

    val ct = canonicalizeType(Function(tv2, tv3))
    val sct = simplifyType(ct)
    val csct = coalesceCompactType(sct).show
    assert(csct == "'a ∧ ('b -> 'b) -> 'a ∨ 'b") // cycle length 6
  }
   */

  /*
  test("hello world") {
    val typer = new Typer(false) with TypeSimplifier
    import typer.{assert => _, _}
    val tv0, tv1 = freshVar(0)
    tv0.upperBounds = Primitive("bool") :: Nil
    tv1.lowerBounds = Primitive("bool") :: Nil

    {
      val ct = canonicalizeType(Function(tv0, tv1))
      val sct = simplifyType(ct)
      val csct = coalesceCompactType(sct).show
      assert(csct == "bool -> bool")
    }

    {
      val ct = canonicalizeType(Function(tv1, tv0))
      val sct = simplifyType(ct)
      val csct = coalesceCompactType(sct).show
      assert(csct == "⊤ -> ⊥")
    }

  }
   */

  /*
  test("hello world") {
    val typer = new Typer(false) with TypeSimplifier
    import typer.{assert => _, _}
    val tv0, tv1 = freshVar(0)

//    tv0.upperBounds = Primitive("bool") :: Nil
//    tv1.lowerBounds = Primitive("bool") :: Nil

    val ct = canonicalizeType(Function(tv1, tv0))
    val sct = simplifyType(ct)
    val csct = coalesceCompactType(sct).show
    assert(csct == "⊤ -> ⊥")
  }
   */

  /*
  test("interesting") {
    val typer = new Typer(false) with TypeSimplifier
    import typer.{assert => _, _}
    val tv0, tv1 , tv2 = freshVar(0)

    tv2.upperBounds = Function(tv0, tv0) :: Function(tv1 , tv1 ) :: Nil

    val ct = canonicalizeType(Function(tv2, Primitive("bool")))
    val sct = simplifyType(ct)
    val csct = coalesceCompactType(sct).show
    assert(csct == "('a -> 'a) -> bool")
  }
  */

  test("interesting") {
    val typer = new Typer(false) with TypeSimplifier
    import typer.{assert => _, _}
    val tv0, tv1 , tv2 , tv3 = freshVar(0)

    tv1.upperBounds =  tv0 :: Nil
    tv2.upperBounds = tv0 :: Nil
    tv3.lowerBounds = tv1 :: tv2 :: Nil

    val ct = canonicalizeType(Function(tv1, Function(tv2 , tv3)))
    val sct = simplifyType(ct)
    val csct = coalesceCompactType(sct).show
    assert(csct == "'a -> 'a -> 'a")
  }

}
