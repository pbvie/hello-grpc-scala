/*
 * Copyright 2016 Petra Bierleutgeb
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.ontherocks.hellogrpc
package clock

import io.grpc.stub.StreamObserver

class ClockService extends ClockGrpc.Clock {

  def getTime(request: TimeRequest, responseObserver: StreamObserver[TimeResponse]): Unit = {
    for (i <- 1 to 10) { // limit to the next ten seconds
      val currentTime = System.currentTimeMillis()
      responseObserver.onNext(TimeResponse(currentTime))
      Thread.sleep(1000) // just for demo purposes - don't do this
    }
    responseObserver.onCompleted()
  }

}
