package ch6

object Ch6 extends App {
  println(new Rational(1, 1))
}

import scala.annotation.tailrec

class Rational(n: Int, d: Int) {
  require(d != 0)

  private val g = gcd(n.abs, d.abs)

  val numer: Int = n / g
  val denom: Int = d / g

  // 補助コンストラクタ
  // 自然数をコンストラクトしたいときに分母の1を省略してnewできるようにする
  // example
  // val b = new Rational(2)
  // val b: Rational = 2/1
  def this(numer: Int) = this(numer, 1)

  // toStringメソッドのオーバーライド
  override def toString: String = numer + "/" + denom

  def +(that: Rational): Rational =
    new Rational(
      this.numer * that.denom + that.numer * this.denom,
      this.denom * that.denom
    )
  def +(i: Int): Rational =
    new Rational(numer + i * denom, denom)

  def -(that: Rational): Rational =
    new Rational(
      this.numer * that.denom - that.numer * this.denom,
      this.denom * that.denom
    )
  def -(i: Int): Rational =
    new Rational(numer - i * denom, denom)

  def *(that: Rational): Rational =
    new Rational(
      this.numer * that.numer,
      this.denom * that.denom
    )
  def *(i: Int): Rational =
    new Rational(this.numer * i, this.denom)

  def /(that: Rational): Rational =
    new Rational(
      this.numer * that.denom,
      this.denom * that.numer
    )
  def /(i: Int): Rational =
    new Rational(this.numer, this.denom * i)

  // thisの方がthatより小さいか
  def lessThan(that: Rational): Boolean =
    this.numer * that.denom < that.numer * this.denom

  // thisとthatの大きい方を返す
  def max(that: Rational): Rational =
    if (this lessThan that) that else this

  // aとbの最大公約数
  @tailrec
  private def gcd(a: Int, b: Int): Int =
    if (b == 0) a else this.gcd(b, a % b)
}
