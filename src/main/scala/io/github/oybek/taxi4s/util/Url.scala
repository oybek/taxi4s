package io.github.oybek.taxi4s.util

trait Url[T] {
  def url(t: T): String
}

object Url {
  def derive[A](f: A => String): Url[A] =
    (a: A) => f(a)

  def from[T](x: T)(implicit urlEncoder: Url[T]): String = {
    urlEncoder.url(x)
  }
}
