package examples.types.info.types

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

import java.util.Date
import java.text.SimpleDateFormat

case class Info(what: String) {

  override def toString = {
    val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    val when = simpleDateFormat.format(new Date())
    val who = Thread.currentThread.getId() 
    s"[INFO] when $when who $who what $what\n"
  }

}
