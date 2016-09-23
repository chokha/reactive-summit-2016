/*
 * The Reactive Summit Austin talk
 * Copyright (C) 2016 Jan Machacek
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.eigengo.rsa.identity.v100

import java.io.FileOutputStream

import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class FaceExtractorTest extends FlatSpec with PropertyChecks with Matchers {
  def getResourceBytes(resourceName: String): Array[Byte] = {
    val is = getClass.getResourceAsStream(resourceName)
    Stream.continually(is.read).takeWhile(_ != -1).map(_.toByte).toArray
  }

  it should "find faces in image" in {
    println(FaceExtractor().extract(getResourceBytes("/dogface.jpg")))
    val x = FaceExtractor().extract(getResourceBytes("/verified.jpg")).head
    new FileOutputStream("/Users/janmachacek/Desktop/x.jpg").write(x.rgbBitmap.toByteArray)
    val y = FaceExtractor().extract(getResourceBytes("/impostor.jpg")).head
    new FileOutputStream("/Users/janmachacek/Desktop/y.jpg").write(y.rgbBitmap.toByteArray)
  }

}
