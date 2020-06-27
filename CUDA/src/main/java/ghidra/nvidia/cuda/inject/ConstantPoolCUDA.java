/* ###
 * IP: GHIDRA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ghidra.nvidia.cuda.inject;

import java.io.IOException;

import ghidra.program.model.data.*;
import ghidra.program.model.lang.ConstantPool;
import ghidra.program.model.listing.Program;

public class ConstantPoolCUDA extends ConstantPool {

    private Program program;

    public ConstantPoolCUDA(Program program) throws IOException {
        this.program = program;
    }

    private String getField(long i) {
        String s = null;
        if (i == 0)
            s = "&threadIdx";
        if (i == 1)
            s = "&threadIdx.x";
        if (i == 2)
            s = "&threadIdx.y";
        if (i == 3)
            s = "&threadIdx.z";
        if (i == 4)
            s = "&blockIdx";
        if (i == 5)
            s = "&blockIdx.x";
        if (i == 6)
            s = "&blockIdx.y";
        if (i == 7)
            s = "&blockIdx.z";
        return s;
    }

    private DataType getDataType(long i) {
        DataType dt = null;
        if (i == 0 || i == 1 || i == 2 || i == 3|| i == 4|| i == 5|| i == 6|| i == 7)
            dt = new UnsignedIntegerDataType();
        return dt;
    }

    @Override
    public Record getRecord(long[] ref) {
        Record res = new Record();
        res.tag = ConstantPool.POINTER_FIELD;
        res.token = getField(ref[0]);
        res.type = new PointerDataType(getDataType(ref[0]));
        return res;
    }

}
