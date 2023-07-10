/*
 * Copyright 2011 Henry Coles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.pitest.mutationtest;

import org.pitest.extensions.MutationRandomizerSingleton;
import org.pitest.functional.Option;
import org.pitest.mutationtest.engine.MutationDetails;

public final class MutationResult {

  private final MutationDetails        details;
  private final MutationStatusTestPair status;

  public MutationResult(final MutationDetails md,
      final MutationStatusTestPair status) {
    this.details = md;
    this.status = status;
    String statusInfo = String.valueOf(status.getKillingTest());
   // if(!statusInfo.contains("org.pitest.functional"))
    if(!status.getStatus().isDetected())
    {
      statusInfo = "NONE";
    }

    MutationRandomizerSingleton.FileName = md.getFilename();
      String GT_id = MutationRandomizerSingleton.FileName.replace('_',';').replaceAll(".ini","");
      if(GT_id.length() <2){
        GT_id = GT_id   +";" + GT_id;
      }

      MutationRandomizerSingleton.DetailReportLog+=""
              + MutationRandomizerSingleton.FileName
              +";" + GT_id
              +";" + status.getStatus().name()
              +";" + md.getTestsInOrder().get(0).getName()
              + ";"+ statusInfo
              + ";" + md.getLineNumber()
              + ";" + md.getFilename()
              + ";" + md.getDescription()
              + System.lineSeparator();
      System.out.println("Debug Info XXO ---- Status: " + status.getStatus().name() + " File:" + MutationRandomizerSingleton.FileName + " KT: "+ statusInfo + " ln: " + md.getLineNumber() + " Class: " + md.getFilename()  +" desc: " + md.getDescription() );
  }

  public MutationDetails getDetails() {
    return this.details;
  }

  public Option<String> getKillingTest() {
    return this.status.getKillingTest();
  }

  public DetectionStatus getStatus() {
    return this.status.getStatus();
  }

  public int getNumberOfTestsRun() {
    return this.status.getNumberOfTestsRun();
  }

  public MutationStatusTestPair getStatusTestPair() {
    return this.status;
  }

  public String getStatusDescription() {
    return getStatus().name();
  }

  public String getKillingTestDescription() {
    return getKillingTest().getOrElse("none");
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result)
        + ((this.details == null) ? 0 : this.details.hashCode());
    result = (prime * result)
        + ((this.status == null) ? 0 : this.status.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final MutationResult other = (MutationResult) obj;
    if (this.details == null) {
      if (other.details != null) {
        return false;
      }
    } else if (!this.details.equals(other.details)) {
      return false;
    }
    if (this.status == null) {
      if (other.status != null) {
        return false;
      }
    } else if (!this.status.equals(other.status)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "MutationResult [details=" + this.details + ", status="
        + this.status + "]";
  }

}