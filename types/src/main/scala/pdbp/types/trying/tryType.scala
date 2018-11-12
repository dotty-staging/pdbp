package pdbp.types.trying

//       _______         __    __        _______
//      / ___  /\       / /\  / /\      / ___  /\
//     / /__/ / / _____/ / / / /_/__   / /__/ / /
//    / _____/ / / ___  / / / ___  /\ /____  / /
//   / /\____\/ / /__/ / / / /__/ / / \___/ / /
//  /_/ /      /______/ / /______/ /     /_/ /
//  \_\/       \______\/  \______\/      \_\/
//                                           v1.0
//  Program Description Based Programming Library
//  author        Luc Duponcheel        2017-2018

object tryType {

  case class Ok[+Z](z: Z)
  case class Ko(t: Throwable)

  type Try[+Z] = Ok[Z] | Ko

  def ok[Z](z: Z): Try[Z] = Ok(z)
  def ko[Z](t: Throwable): Try[Z] = Ko(t)

}
