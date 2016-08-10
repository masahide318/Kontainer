
# Kontainer Dependency Injection

Kontainer is one source container

## Example

```kotlin

class SampleObj(val id: Int, val name: String) {
}

koRegister("sample",{SampleObj(1,"hoge")})

//inject with new Object
val sampleObj1:SampleObj = koInject("sample")
val sampleObj2:SampleObj = koInject("sample")
println(sampleObj1 === sampleObj2) //false

//inject with singleton
val singleton1:SampleObj = koInjectSingleton("sample")
val singleton2:SampleObj = koInjectSingleton("sample")
println(singleton1 === singleton2) // true

```

License

Copyright 2016 Masahide Takahata

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.