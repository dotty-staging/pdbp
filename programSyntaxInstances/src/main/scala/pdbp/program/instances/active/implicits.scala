package pdbp.program.instances.active

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

import pdbp.types.implicitUnit._
import pdbp.types.implicitFunctionType._
import pdbp.types.Thunk
import pdbp.types.product.productType._

import pdbp.program.Program

import pdbp.computation.Computation

import pdbp.program.instances.types.active.activeTypes._

import pdbp.program.instances.utils.active.functionUtils._

object implicits {

  implicit object program extends Computation[Active] with Program[`=>A`] {

    override private[pdbp] def result[Z]: Z => Active[Z] = `z=>az`

    override private[pdbp] def bind[Z, Y]
      : (Active[Z] && Thunk[Z => Active[Y]]) => Active[Y] = { (az, `z=>ay`) =>
      `z=>ay`.eval(az)
    }

  }

}
