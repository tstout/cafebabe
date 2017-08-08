(ns cafebabe.gen-opcodes
  (:require [pl.danieljanus.tagsoup :refer [parse]]))

;;
;; This was used to do a one-time generation of the JVM op-code table.
;;

;(def op-page
;  (parse "https://en.wikipedia.org/wiki/Java_bytecode_instruction_listings"))


(def op-table [])
  ;[:table
  ; {:class "wikitable sortable"}
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "aaload"]
  ;  [:td {:colspan "1", :rowspan "1"} "32"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 0010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index → value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load onto the stack a reference from an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "aastore"]
  ;  [:td {:colspan "1", :rowspan "1"} "53"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 0011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index, value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store into a reference in an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "aconst_null"]
  ;  [:td {:colspan "1", :rowspan "1"} "01"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 0001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ null"]
  ;  [:td {:colspan "1", :rowspan "1"} "push a " [:i {} "null"] " reference onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "aload"]
  ;  [:td {:colspan "1", :rowspan "1"} "19"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 1001"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: index"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ objectref"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a reference onto the stack from a local variable " [:i {} "#index"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "aload_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "2a"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 1010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ objectref"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a reference onto the stack from local variable 0"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "aload_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "2b"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 1011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ objectref"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a reference onto the stack from local variable 1"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "aload_2"]
  ;  [:td {:colspan "1", :rowspan "1"} "2c"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 1100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ objectref"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a reference onto the stack from local variable 2"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "aload_3"]
  ;  [:td {:colspan "1", :rowspan "1"} "2d"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 1101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ objectref"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a reference onto the stack from local variable 3"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "anewarray"]
  ;  [:td {:colspan "1", :rowspan "1"} "bd"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 1101"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "count → arrayref"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "create a new array of references of length "
  ;   [:i {} "count"]
  ;   " and component type identified by the class reference "
  ;   [:i {} "index"]
  ;   " ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ") in the constant pool"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "areturn"]
  ;  [:td {:colspan "1", :rowspan "1"} "b0"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 0000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref → [empty]"]
  ;  [:td {:colspan "1", :rowspan "1"} "return a reference from a method"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "arraylength"]
  ;  [:td {:colspan "1", :rowspan "1"} "be"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 1110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref → length"]
  ;  [:td {:colspan "1", :rowspan "1"} "get the length of an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "astore"]
  ;  [:td {:colspan "1", :rowspan "1"} "3a"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 1010"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: index"]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a reference into a local variable " [:i {} "#index"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "astore_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "4b"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 1011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a reference into local variable 0"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "astore_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "4c"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 1100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a reference into local variable 1"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "astore_2"]
  ;  [:td {:colspan "1", :rowspan "1"} "4d"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 1101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a reference into local variable 2"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "astore_3"]
  ;  [:td {:colspan "1", :rowspan "1"} "4e"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 1110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a reference into local variable 3"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "athrow"]
  ;  [:td {:colspan "1", :rowspan "1"} "bf"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 1111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref → [empty], objectref"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "throws an error or exception (notice that the rest of the stack is cleared, leaving only a reference to the Throwable)"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "baload"]
  ;  [:td {:colspan "1", :rowspan "1"} "33"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 0011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index → value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a byte or Boolean value from an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "bastore"]
  ;  [:td {:colspan "1", :rowspan "1"} "54"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 0100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index, value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a byte or Boolean value into an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "bipush"]
  ;  [:td {:colspan "1", :rowspan "1"} "10"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 0000"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: byte"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "push a " [:i {} "byte"] " onto the stack as an integer " [:i {} "value"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "breakpoint"]
  ;  [:td {:colspan "1", :rowspan "1"} "ca"]
  ;  [:td {:colspan "1", :rowspan "1"} "1100 1010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "reserved for breakpoints in Java debuggers; should not appear in any class file"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "caload"]
  ;  [:td {:colspan "1", :rowspan "1"} "34"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 0100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index → value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a char from an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "castore"]
  ;  [:td {:colspan "1", :rowspan "1"} "55"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 0101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index, value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a char into an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "checkcast"]
  ;  [:td {:colspan "1", :rowspan "1"} "c0"]
  ;  [:td {:colspan "1", :rowspan "1"} "1100 0000"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref → objectref"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "checks whether an "
  ;   [:i {} "objectref"]
  ;   " is of a certain type, the class reference of which is in the constant pool at "
  ;   [:i {} "index"]
  ;   " ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "d2f"]
  ;  [:td {:colspan "1", :rowspan "1"} "90"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 0000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert a double to a float"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "d2i"]
  ;  [:td {:colspan "1", :rowspan "1"} "8e"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 1110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert a double to an int"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "d2l"]
  ;  [:td {:colspan "1", :rowspan "1"} "8f"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 1111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert a double to a long"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dadd"]
  ;  [:td {:colspan "1", :rowspan "1"} "63"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 0011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "add two doubles"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "daload"]
  ;  [:td {:colspan "1", :rowspan "1"} "31"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 0001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index → value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a double from an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dastore"]
  ;  [:td {:colspan "1", :rowspan "1"} "52"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 0010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index, value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a double into an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dcmpg"]
  ;  [:td {:colspan "1", :rowspan "1"} "98"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 1000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "compare two doubles"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dcmpl"]
  ;  [:td {:colspan "1", :rowspan "1"} "97"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 0111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "compare two doubles"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dconst_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "0e"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 1110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 0.0"]
  ;  [:td {:colspan "1", :rowspan "1"} "push the constant " [:i {} "0.0"] " (a " [:i {} "double"] ") onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dconst_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "0f"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 1111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 1.0"]
  ;  [:td {:colspan "1", :rowspan "1"} "push the constant " [:i {} "1.0"] " (a " [:i {} "double"] ") onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ddiv"]
  ;  [:td {:colspan "1", :rowspan "1"} "6f"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 1111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "divide two doubles"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dload"]
  ;  [:td {:colspan "1", :rowspan "1"} "18"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 1000"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: index"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a double " [:i {} "value"] " from a local variable " [:i {} "#index"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dload_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "26"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 0110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a double from local variable 0"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dload_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "27"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 0111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a double from local variable 1"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dload_2"]
  ;  [:td {:colspan "1", :rowspan "1"} "28"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 1000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a double from local variable 2"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dload_3"]
  ;  [:td {:colspan "1", :rowspan "1"} "29"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 1001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a double from local variable 3"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dmul"]
  ;  [:td {:colspan "1", :rowspan "1"} "6b"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 1011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "multiply two doubles"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dneg"]
  ;  [:td {:colspan "1", :rowspan "1"} "77"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 0111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "negate a double"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "drem"]
  ;  [:td {:colspan "1", :rowspan "1"} "73"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 0011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "get the remainder from a division between two doubles"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dreturn"]
  ;  [:td {:colspan "1", :rowspan "1"} "af"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 1111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → [empty]"]
  ;  [:td {:colspan "1", :rowspan "1"} "return a double from a method"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dstore"]
  ;  [:td {:colspan "1", :rowspan "1"} "39"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 1001"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: index"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a double " [:i {} "value"] " into a local variable " [:i {} "#index"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dstore_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "47"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 0111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a double into local variable 0"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dstore_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "48"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 1000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a double into local variable 1"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dstore_2"]
  ;  [:td {:colspan "1", :rowspan "1"} "49"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 1001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a double into local variable 2"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dstore_3"]
  ;  [:td {:colspan "1", :rowspan "1"} "4a"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 1010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a double into local variable 3"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dsub"]
  ;  [:td {:colspan "1", :rowspan "1"} "67"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 0111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "subtract a double from another"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dup"]
  ;  [:td {:colspan "1", :rowspan "1"} "59"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 1001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → value, value"]
  ;  [:td {:colspan "1", :rowspan "1"} "duplicate the value on top of the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dup_x1"]
  ;  [:td {:colspan "1", :rowspan "1"} "5a"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 1010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value2, value1 → value1, value2, value1"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "insert a copy of the top value into the stack two values from the top. value1 and value2 must not be of the type double or long."]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dup_x2"]
  ;  [:td {:colspan "1", :rowspan "1"} "5b"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 1011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value3, value2, value1 → value1, value3, value2, value1"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "insert a copy of the top value into the stack two (if value2 is double or long it takes up the entry of value3, too) or three values (if value2 is neither double nor long) from the top"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dup2"]
  ;  [:td {:colspan "1", :rowspan "1"} "5c"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 1100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "{value2, value1} → {value2, value1}, {value2, value1}"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "duplicate top two stack words (two values, if value1 is not double nor long; a single value, if value1 is double or long)"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dup2_x1"]
  ;  [:td {:colspan "1", :rowspan "1"} "5d"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 1101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value3, {value2, value1} → {value2, value1}, value3, {value2, value1}"]
  ;  [:td {:colspan "1", :rowspan "1"} "duplicate two words and insert beneath third word (see explanation above)"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "dup2_x2"]
  ;  [:td {:colspan "1", :rowspan "1"} "5e"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 1110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "{value4, value3}, {value2, value1} → {value2, value1}, {value4, value3}, {value2, value1}"]
  ;  [:td {:colspan "1", :rowspan "1"} "duplicate two words and insert beneath fourth word"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "f2d"]
  ;  [:td {:colspan "1", :rowspan "1"} "8d"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 1101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert a float to a double"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "f2i"]
  ;  [:td {:colspan "1", :rowspan "1"} "8b"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 1011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert a float to an int"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "f2l"]
  ;  [:td {:colspan "1", :rowspan "1"} "8c"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 1100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert a float to a long"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fadd"]
  ;  [:td {:colspan "1", :rowspan "1"} "62"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 0010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "add two floats"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "faload"]
  ;  [:td {:colspan "1", :rowspan "1"} "30"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 0000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index → value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a float from an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fastore"]
  ;  [:td {:colspan "1", :rowspan "1"} "51"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 0001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index, value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a float in an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fcmpg"]
  ;  [:td {:colspan "1", :rowspan "1"} "96"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 0110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "compare two floats"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fcmpl"]
  ;  [:td {:colspan "1", :rowspan "1"} "95"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 0101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "compare two floats"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fconst_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "0b"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 1011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 0.0f"]
  ;  [:td {:colspan "1", :rowspan "1"} "push " [:i {} "0.0f"] " on the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fconst_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "0c"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 1100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 1.0f"]
  ;  [:td {:colspan "1", :rowspan "1"} "push " [:i {} "1.0f"] " on the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fconst_2"]
  ;  [:td {:colspan "1", :rowspan "1"} "0d"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 1101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 2.0f"]
  ;  [:td {:colspan "1", :rowspan "1"} "push " [:i {} "2.0f"] " on the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fdiv"]
  ;  [:td {:colspan "1", :rowspan "1"} "6e"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 1110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "divide two floats"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fload"]
  ;  [:td {:colspan "1", :rowspan "1"} "17"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 0111"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: index"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a float " [:i {} "value"] " from a local variable " [:i {} "#index"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fload_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "22"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 0010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a float " [:i {} "value"] " from local variable 0"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fload_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "23"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 0011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a float " [:i {} "value"] " from local variable 1"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fload_2"]
  ;  [:td {:colspan "1", :rowspan "1"} "24"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 0100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a float " [:i {} "value"] " from local variable 2"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fload_3"]
  ;  [:td {:colspan "1", :rowspan "1"} "25"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 0101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a float " [:i {} "value"] " from local variable 3"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fmul"]
  ;  [:td {:colspan "1", :rowspan "1"} "6a"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 1010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "multiply two floats"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fneg"]
  ;  [:td {:colspan "1", :rowspan "1"} "76"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 0110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "negate a float"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "frem"]
  ;  [:td {:colspan "1", :rowspan "1"} "72"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 0010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "get the remainder from a division between two floats"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "freturn"]
  ;  [:td {:colspan "1", :rowspan "1"} "ae"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 1110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → [empty]"]
  ;  [:td {:colspan "1", :rowspan "1"} "return a float"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fstore"]
  ;  [:td {:colspan "1", :rowspan "1"} "38"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 1000"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: index"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a float " [:i {} "value"] " into a local variable " [:i {} "#index"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fstore_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "43"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 0011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a float " [:i {} "value"] " into local variable 0"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fstore_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "44"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 0100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a float " [:i {} "value"] " into local variable 1"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fstore_2"]
  ;  [:td {:colspan "1", :rowspan "1"} "45"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 0101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a float " [:i {} "value"] " into local variable 2"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fstore_3"]
  ;  [:td {:colspan "1", :rowspan "1"} "46"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 0110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a float " [:i {} "value"] " into local variable 3"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "fsub"]
  ;  [:td {:colspan "1", :rowspan "1"} "66"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 0110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "subtract two floats"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "getfield"]
  ;  [:td {:colspan "1", :rowspan "1"} "b4"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 0100"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref → value"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "get a field "
  ;   [:i {} "value"]
  ;   " of an object "
  ;   [:i {} "objectref"]
  ;   ", where the field is identified by field reference in the constant pool "
  ;   [:i {} "index"]
  ;   " ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "getstatic"]
  ;  [:td {:colspan "1", :rowspan "1"} "b2"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 0010"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "get a static field "
  ;   [:i {} "value"]
  ;   " of a class, where the field is identified by field reference in the constant pool "
  ;   [:i {} "index"]
  ;   " ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "goto"]
  ;  [:td {:colspan "1", :rowspan "1"} "a7"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 0111"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "[no change]"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "goes to another instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "goto_w"]
  ;  [:td {:colspan "1", :rowspan "1"} "c8"]
  ;  [:td {:colspan "1", :rowspan "1"} "1100 1000"]
  ;  [:td {:colspan "1", :rowspan "1"} "4: branchbyte1, branchbyte2, branchbyte3, branchbyte4"]
  ;  [:td {:colspan "1", :rowspan "1"} "[no change]"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "goes to another instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed int constructed from unsigned bytes "
  ;   [:span
  ;    {:style "font-family: monospace, monospace;"}
  ;    "branchbyte1 << 24 + branchbyte2 << 16 + branchbyte3 << 8 + branchbyte4"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "i2b"]
  ;  [:td {:colspan "1", :rowspan "1"} "91"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 0001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert an int into a byte"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "i2c"]
  ;  [:td {:colspan "1", :rowspan "1"} "92"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 0010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert an int into a character"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "i2d"]
  ;  [:td {:colspan "1", :rowspan "1"} "87"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 0111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert an int into a double"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "i2f"]
  ;  [:td {:colspan "1", :rowspan "1"} "86"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 0110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert an int into a float"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "i2l"]
  ;  [:td {:colspan "1", :rowspan "1"} "85"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 0101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert an int into a long"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "i2s"]
  ;  [:td {:colspan "1", :rowspan "1"} "93"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 0011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert an int into a short"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iadd"]
  ;  [:td {:colspan "1", :rowspan "1"} "60"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 0000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "add two ints"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iaload"]
  ;  [:td {:colspan "1", :rowspan "1"} "2e"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 1110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index → value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load an int from an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iand"]
  ;  [:td {:colspan "1", :rowspan "1"} "7e"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 1110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "perform a bitwise AND on two integers"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iastore"]
  ;  [:td {:colspan "1", :rowspan "1"} "4f"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 1111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index, value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store an int into an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iconst_m1"]
  ;  [:td {:colspan "1", :rowspan "1"} "02"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 0010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ -1"]
  ;  [:td {:colspan "1", :rowspan "1"} "load the int value −1 onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iconst_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "03"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 0011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 0"]
  ;  [:td {:colspan "1", :rowspan "1"} "load the int value 0 onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iconst_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "04"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 0100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 1"]
  ;  [:td {:colspan "1", :rowspan "1"} "load the int value 1 onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iconst_2"]
  ;  [:td {:colspan "1", :rowspan "1"} "05"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 0101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 2"]
  ;  [:td {:colspan "1", :rowspan "1"} "load the int value 2 onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iconst_3"]
  ;  [:td {:colspan "1", :rowspan "1"} "06"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 0110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 3"]
  ;  [:td {:colspan "1", :rowspan "1"} "load the int value 3 onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iconst_4"]
  ;  [:td {:colspan "1", :rowspan "1"} "07"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 0111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 4"]
  ;  [:td {:colspan "1", :rowspan "1"} "load the int value 4 onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iconst_5"]
  ;  [:td {:colspan "1", :rowspan "1"} "08"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 1000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 5"]
  ;  [:td {:colspan "1", :rowspan "1"} "load the int value 5 onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "idiv"]
  ;  [:td {:colspan "1", :rowspan "1"} "6c"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 1100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "divide two integers"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "if_acmpeq"]
  ;  [:td {:colspan "1", :rowspan "1"} "a5"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 0101"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if references are equal, branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "if_acmpne"]
  ;  [:td {:colspan "1", :rowspan "1"} "a6"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 0110"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if references are not equal, branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "if_icmpeq"]
  ;  [:td {:colspan "1", :rowspan "1"} "9f"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 1111"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if ints are equal, branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "if_icmpge"]
  ;  [:td {:colspan "1", :rowspan "1"} "a2"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 0010"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if "
  ;   [:i {} "value1"]
  ;   " is greater than or equal to "
  ;   [:i {} "value2"]
  ;   ", branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "if_icmpgt"]
  ;  [:td {:colspan "1", :rowspan "1"} "a3"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 0011"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if "
  ;   [:i {} "value1"]
  ;   " is greater than "
  ;   [:i {} "value2"]
  ;   ", branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "if_icmple"]
  ;  [:td {:colspan "1", :rowspan "1"} "a4"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 0100"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if "
  ;   [:i {} "value1"]
  ;   " is less than or equal to "
  ;   [:i {} "value2"]
  ;   ", branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "if_icmplt"]
  ;  [:td {:colspan "1", :rowspan "1"} "a1"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 0001"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if "
  ;   [:i {} "value1"]
  ;   " is less than "
  ;   [:i {} "value2"]
  ;   ", branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "if_icmpne"]
  ;  [:td {:colspan "1", :rowspan "1"} "a0"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 0000"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if ints are not equal, branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ifeq"]
  ;  [:td {:colspan "1", :rowspan "1"} "99"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 1001"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if "
  ;   [:i {} "value"]
  ;   " is 0, branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ifge"]
  ;  [:td {:colspan "1", :rowspan "1"} "9c"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 1100"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if "
  ;   [:i {} "value"]
  ;   " is greater than or equal to 0, branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ifgt"]
  ;  [:td {:colspan "1", :rowspan "1"} "9d"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 1101"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if "
  ;   [:i {} "value"]
  ;   " is greater than 0, branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ifle"]
  ;  [:td {:colspan "1", :rowspan "1"} "9e"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 1110"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if "
  ;   [:i {} "value"]
  ;   " is less than or equal to 0, branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iflt"]
  ;  [:td {:colspan "1", :rowspan "1"} "9b"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 1011"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if "
  ;   [:i {} "value"]
  ;   " is less than 0, branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ifne"]
  ;  [:td {:colspan "1", :rowspan "1"} "9a"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 1010"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if "
  ;   [:i {} "value"]
  ;   " is not 0, branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ifnonnull"]
  ;  [:td {:colspan "1", :rowspan "1"} "c7"]
  ;  [:td {:colspan "1", :rowspan "1"} "1100 0111"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if "
  ;   [:i {} "value"]
  ;   " is not null, branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ifnull"]
  ;  [:td {:colspan "1", :rowspan "1"} "c6"]
  ;  [:td {:colspan "1", :rowspan "1"} "1100 0110"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "if "
  ;   [:i {} "value"]
  ;   " is null, branch to instruction at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iinc"]
  ;  [:td {:colspan "1", :rowspan "1"} "84"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 0100"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: index, const"]
  ;  [:td {:colspan "1", :rowspan "1"} "[No change]"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "increment local variable "
  ;   [:i {} "#index"]
  ;   " by signed byte "
  ;   [:i {} "const"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iload"]
  ;  [:td {:colspan "1", :rowspan "1"} "15"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 0101"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: index"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load an int " [:i {} "value"] " from a local variable " [:i {} "#index"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iload_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "1a"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 1010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load an int " [:i {} "value"] " from local variable 0"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iload_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "1b"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 1011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load an int " [:i {} "value"] " from local variable 1"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iload_2"]
  ;  [:td {:colspan "1", :rowspan "1"} "1c"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 1100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load an int " [:i {} "value"] " from local variable 2"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iload_3"]
  ;  [:td {:colspan "1", :rowspan "1"} "1d"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 1101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load an int " [:i {} "value"] " from local variable 3"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "impdep1"]
  ;  [:td {:colspan "1", :rowspan "1"} "fe"]
  ;  [:td {:colspan "1", :rowspan "1"} "1111 1110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "reserved for implementation-dependent operations within debuggers; should not appear in any class file"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "impdep2"]
  ;  [:td {:colspan "1", :rowspan "1"} "ff"]
  ;  [:td {:colspan "1", :rowspan "1"} "1111 1111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "reserved for implementation-dependent operations within debuggers; should not appear in any class file"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "imul"]
  ;  [:td {:colspan "1", :rowspan "1"} "68"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 1000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "multiply two integers"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ineg"]
  ;  [:td {:colspan "1", :rowspan "1"} "74"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 0100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "negate int"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "instanceof"]
  ;  [:td {:colspan "1", :rowspan "1"} "c1"]
  ;  [:td {:colspan "1", :rowspan "1"} "1100 0001"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref → result"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "determines if an object "
  ;   [:i {} "objectref"]
  ;   " is of a given type, identified by class reference "
  ;   [:i {} "index"]
  ;   " in constant pool ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "invokedynamic"]
  ;  [:td {:colspan "1", :rowspan "1"} "ba"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 1010"]
  ;  [:td {:colspan "1", :rowspan "1"} "4: indexbyte1, indexbyte2, 0, 0"]
  ;  [:td {:colspan "1", :rowspan "1"} "[arg1, [arg2 ...]] → result"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "invokes a dynamic method and puts the result on the stack (might be void); the method is identified by method reference "
  ;   [:i {} "index"]
  ;   " in constant pool ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "invokeinterface"]
  ;  [:td {:colspan "1", :rowspan "1"} "b9"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 1001"]
  ;  [:td {:colspan "1", :rowspan "1"} "4: indexbyte1, indexbyte2, count, 0"]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref, [arg1, arg2, ...] → result"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "invokes an interface method on object "
  ;   [:i {} "objectref"]
  ;   " and puts the result on the stack (might be void); the interface method is identified by method reference "
  ;   [:i {} "index"]
  ;   " in constant pool ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "invokespecial"]
  ;  [:td {:colspan "1", :rowspan "1"} "b7"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 0111"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref, [arg1, arg2, ...] → result"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "invoke instance method on object "
  ;   [:i {} "objectref"]
  ;   " and puts the result on the stack (might be void); the method is identified by method reference "
  ;   [:i {} "index"]
  ;   " in constant pool ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "invokestatic"]
  ;  [:td {:colspan "1", :rowspan "1"} "b8"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 1000"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "[arg1, arg2, ...] → result"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "invoke a static method and puts the result on the stack (might be void); the method is identified by method reference "
  ;   [:i {} "index"]
  ;   " in constant pool ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "invokevirtual"]
  ;  [:td {:colspan "1", :rowspan "1"} "b6"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 0110"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref, [arg1, arg2, ...] → result"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "invoke virtual method on object "
  ;   [:i {} "objectref"]
  ;   " and puts the result on the stack (might be void); the method is identified by method reference "
  ;   [:i {} "index"]
  ;   " in constant pool ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ior"]
  ;  [:td {:colspan "1", :rowspan "1"} "80"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 0000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "bitwise int OR"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "irem"]
  ;  [:td {:colspan "1", :rowspan "1"} "70"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 0000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "logical int remainder"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ireturn"]
  ;  [:td {:colspan "1", :rowspan "1"} "ac"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 1100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → [empty]"]
  ;  [:td {:colspan "1", :rowspan "1"} "return an integer from a method"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ishl"]
  ;  [:td {:colspan "1", :rowspan "1"} "78"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 1000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "int shift left"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ishr"]
  ;  [:td {:colspan "1", :rowspan "1"} "7a"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 1010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "int arithmetic shift right"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "istore"]
  ;  [:td {:colspan "1", :rowspan "1"} "36"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 0110"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: index"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store int " [:i {} "value"] " into variable " [:i {} "#index"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "istore_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "3b"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 1011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store int " [:i {} "value"] " into variable 0"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "istore_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "3c"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 1100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store int " [:i {} "value"] " into variable 1"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "istore_2"]
  ;  [:td {:colspan "1", :rowspan "1"} "3d"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 1101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store int " [:i {} "value"] " into variable 2"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "istore_3"]
  ;  [:td {:colspan "1", :rowspan "1"} "3e"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 1110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store int " [:i {} "value"] " into variable 3"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "isub"]
  ;  [:td {:colspan "1", :rowspan "1"} "64"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 0100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "int subtract"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "iushr"]
  ;  [:td {:colspan "1", :rowspan "1"} "7c"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 1100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "int logical shift right"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ixor"]
  ;  [:td {:colspan "1", :rowspan "1"} "82"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 0010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "int xor"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "jsr"]
  ;  [:td {:colspan "1", :rowspan "1"} "a8"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 1000"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: branchbyte1, branchbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ address"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "jump to subroutine at "
  ;   [:i {} "branchoffset"]
  ;   " (signed short constructed from unsigned bytes "
  ;   [:span {:style "font-family: monospace, monospace;"} "branchbyte1 << 8 + branchbyte2"]
  ;   ") and place the return address on the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "jsr_w"]
  ;  [:td {:colspan "1", :rowspan "1"} "c9"]
  ;  [:td {:colspan "1", :rowspan "1"} "1100 1001"]
  ;  [:td {:colspan "1", :rowspan "1"} "4: branchbyte1, branchbyte2, branchbyte3, branchbyte4"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ address"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "jump to subroutine at "
  ;   [:i {} "branchoffset"]
  ;   " (signed int constructed from unsigned bytes "
  ;   [:span
  ;    {:style "font-family: monospace, monospace;"}
  ;    "branchbyte1 << 24 + branchbyte2 << 16 + branchbyte3 << 8 + branchbyte4"]
  ;   ") and place the return address on the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "l2d"]
  ;  [:td {:colspan "1", :rowspan "1"} "8a"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 1010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert a long to a double"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "l2f"]
  ;  [:td {:colspan "1", :rowspan "1"} "89"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 1001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert a long to a float"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "l2i"]
  ;  [:td {:colspan "1", :rowspan "1"} "88"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 1000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "convert a long to a int"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ladd"]
  ;  [:td {:colspan "1", :rowspan "1"} "61"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 0001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "add two longs"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "laload"]
  ;  [:td {:colspan "1", :rowspan "1"} "2f"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 1111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index → value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a long from an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "land"]
  ;  [:td {:colspan "1", :rowspan "1"} "7f"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 1111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   [:a {:shape "rect", :href "/wiki/Bitwise_operation", :title "Bitwise operation"} "bitwise"]
  ;   " AND of two longs"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lastore"]
  ;  [:td {:colspan "1", :rowspan "1"} "50"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 0000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index, value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a long to an array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lcmp"]
  ;  [:td {:colspan "1", :rowspan "1"} "94"]
  ;  [:td {:colspan "1", :rowspan "1"} "1001 0100"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "push 0 if the two longs are the same, 1 if value1 is greater than value2, -1 otherwise"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lconst_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "09"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 1001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 0L"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "push "
  ;   [:i {} "0L"]
  ;   " (the number "
  ;   [:a {:shape "rect", :class "mw-redirect", :href "/wiki/Zero", :title "Zero"} "zero"]
  ;   " with type "
  ;   [:i {} "long"]
  ;   ") onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lconst_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "0a"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 1010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ 1L"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "push "
  ;   [:i {} "1L"]
  ;   " (the number "
  ;   [:a {:shape "rect", :class "mw-redirect", :href "/wiki/One", :title "One"} "one"]
  ;   " with type "
  ;   [:i {} "long"]
  ;   ") onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ldc"]
  ;  [:td {:colspan "1", :rowspan "1"} "12"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 0010"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: index"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "push a constant "
  ;   [:i {} "#index"]
  ;   " from a constant pool (String, int, float, Class, java.lang.invoke.MethodType, or java.lang.invoke.MethodHandle) onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ldc_w"]
  ;  [:td {:colspan "1", :rowspan "1"} "13"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 0011"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "push a constant "
  ;   [:i {} "#index"]
  ;   " from a constant pool (String, int, float, Class, java.lang.invoke.MethodType, or java.lang.invoke.MethodHandle) onto the stack (wide "
  ;   [:i {} "index"]
  ;   " is constructed as "
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ldc2_w"]
  ;  [:td {:colspan "1", :rowspan "1"} "14"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 0100"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "push a constant "
  ;   [:i {} "#index"]
  ;   " from a constant pool (double or long) onto the stack (wide "
  ;   [:i {} "index"]
  ;   " is constructed as "
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ldiv"]
  ;  [:td {:colspan "1", :rowspan "1"} "6d"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 1101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "divide two longs"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lload"]
  ;  [:td {:colspan "1", :rowspan "1"} "16"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 0110"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: index"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a long value from a local variable " [:i {} "#index"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lload_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "1e"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 1110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a long value from a local variable 0"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lload_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "1f"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 1111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a long value from a local variable 1"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lload_2"]
  ;  [:td {:colspan "1", :rowspan "1"} "20"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 0000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a long value from a local variable 2"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lload_3"]
  ;  [:td {:colspan "1", :rowspan "1"} "21"]
  ;  [:td {:colspan "1", :rowspan "1"} "0010 0001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load a long value from a local variable 3"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lmul"]
  ;  [:td {:colspan "1", :rowspan "1"} "69"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 1001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "multiply two longs"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lneg"]
  ;  [:td {:colspan "1", :rowspan "1"} "75"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 0101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "negate a long"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lookupswitch"]
  ;  [:td {:colspan "1", :rowspan "1"} "ab"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 1011"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "4+: <0–3 bytes padding>, defaultbyte1, defaultbyte2, defaultbyte3, defaultbyte4, npairs1, npairs2, npairs3, npairs4, match-offset pairs..."]
  ;  [:td {:colspan "1", :rowspan "1"} "key →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "a target address is looked up from a table using a key and execution continues from the instruction at that address"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lor"]
  ;  [:td {:colspan "1", :rowspan "1"} "81"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 0001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "bitwise OR of two longs"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lrem"]
  ;  [:td {:colspan "1", :rowspan "1"} "71"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 0001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "remainder of division of two longs"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lreturn"]
  ;  [:td {:colspan "1", :rowspan "1"} "ad"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 1101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value → [empty]"]
  ;  [:td {:colspan "1", :rowspan "1"} "return a long value"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lshl"]
  ;  [:td {:colspan "1", :rowspan "1"} "79"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 1001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "bitwise shift left of a long "
  ;   [:i {} "value1"]
  ;   " by int "
  ;   [:i {} "value2"]
  ;   " positions"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lshr"]
  ;  [:td {:colspan "1", :rowspan "1"} "7b"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 1011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "bitwise shift right of a long "
  ;   [:i {} "value1"]
  ;   " by int "
  ;   [:i {} "value2"]
  ;   " positions"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lstore"]
  ;  [:td {:colspan "1", :rowspan "1"} "37"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 0111"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: index"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a long " [:i {} "value"] " in a local variable " [:i {} "#index"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lstore_0"]
  ;  [:td {:colspan "1", :rowspan "1"} "3f"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 1111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a long " [:i {} "value"] " in a local variable 0"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lstore_1"]
  ;  [:td {:colspan "1", :rowspan "1"} "40"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 0000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a long " [:i {} "value"] " in a local variable 1"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lstore_2"]
  ;  [:td {:colspan "1", :rowspan "1"} "41"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 0001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a long " [:i {} "value"] " in a local variable 2"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lstore_3"]
  ;  [:td {:colspan "1", :rowspan "1"} "42"]
  ;  [:td {:colspan "1", :rowspan "1"} "0100 0010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store a long " [:i {} "value"] " in a local variable 3"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lsub"]
  ;  [:td {:colspan "1", :rowspan "1"} "65"]
  ;  [:td {:colspan "1", :rowspan "1"} "0110 0101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "subtract two longs"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lushr"]
  ;  [:td {:colspan "1", :rowspan "1"} "7d"]
  ;  [:td {:colspan "1", :rowspan "1"} "0111 1101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "bitwise shift right of a long "
  ;   [:i {} "value1"]
  ;   " by int "
  ;   [:i {} "value2"]
  ;   " positions, unsigned"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "lxor"]
  ;  [:td {:colspan "1", :rowspan "1"} "83"]
  ;  [:td {:colspan "1", :rowspan "1"} "1000 0011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value1, value2 → result"]
  ;  [:td {:colspan "1", :rowspan "1"} "bitwise XOR of two longs"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "monitorenter"]
  ;  [:td {:colspan "1", :rowspan "1"} "c2"]
  ;  [:td {:colspan "1", :rowspan "1"} "1100 0010"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "enter monitor for object (\"grab the lock\" – start of synchronized() section)"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "monitorexit"]
  ;  [:td {:colspan "1", :rowspan "1"} "c3"]
  ;  [:td {:colspan "1", :rowspan "1"} "1100 0011"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "exit monitor for object (\"release the lock\" – end of synchronized() section)"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "multianewarray"]
  ;  [:td {:colspan "1", :rowspan "1"} "c5"]
  ;  [:td {:colspan "1", :rowspan "1"} "1100 0101"]
  ;  [:td {:colspan "1", :rowspan "1"} "3: indexbyte1, indexbyte2, dimensions"]
  ;  [:td {:colspan "1", :rowspan "1"} "count1, [count2,...] → arrayref"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "create a new array of "
  ;   [:i {} "dimensions"]
  ;   " dimensions with elements of type identified by class reference in constant pool "
  ;   [:i {} "index"]
  ;   " ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   "); the sizes of each dimension is identified by "
  ;   [:i {} "count1"]
  ;   ", ["
  ;   [:i {} "count2"]
  ;   ", etc.]"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "new"]
  ;  [:td {:colspan "1", :rowspan "1"} "bb"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 1011"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ objectref"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "create new object of type identified by class reference in constant pool "
  ;   [:i {} "index"]
  ;   " ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "newarray"]
  ;  [:td {:colspan "1", :rowspan "1"} "bc"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 1100"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: atype"]
  ;  [:td {:colspan "1", :rowspan "1"} "count → arrayref"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "create new array with "
  ;   [:i {} "count"]
  ;   " elements of primitive type identified by "
  ;   [:i {} "atype"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "nop"]
  ;  [:td {:colspan "1", :rowspan "1"} "00"]
  ;  [:td {:colspan "1", :rowspan "1"} "0000 0000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "[No change]"]
  ;  [:td {:colspan "1", :rowspan "1"} "perform no operation"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "pop"]
  ;  [:td {:colspan "1", :rowspan "1"} "57"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 0111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "discard the top value on the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "pop2"]
  ;  [:td {:colspan "1", :rowspan "1"} "58"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 1000"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "{value2, value1} →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "discard the top two values on the stack (or one value, if it is a double or long)"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "putfield"]
  ;  [:td {:colspan "1", :rowspan "1"} "b5"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 0101"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "objectref, value →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "set field to "
  ;   [:i {} "value"]
  ;   " in an object "
  ;   [:i {} "objectref"]
  ;   ", where the field is identified by a field reference "
  ;   [:i {} "index"]
  ;   " in constant pool ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "putstatic"]
  ;  [:td {:colspan "1", :rowspan "1"} "b3"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 0011"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: indexbyte1, indexbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "value →"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "set static field to "
  ;   [:i {} "value"]
  ;   " in a class, where the field is identified by a field reference "
  ;   [:i {} "index"]
  ;   " in constant pool ("
  ;   [:span {:style "font-family: monospace, monospace;"} "indexbyte1 << 8 + indexbyte2"]
  ;   ")"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "ret"]
  ;  [:td {:colspan "1", :rowspan "1"} "a9"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 1001"]
  ;  [:td {:colspan "1", :rowspan "1"} "1: index"]
  ;  [:td {:colspan "1", :rowspan "1"} "[No change]"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "continue execution from address taken from a local variable "
  ;   [:i {} "#index"]
  ;   " (the asymmetry with jsr is intentional)"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "return"]
  ;  [:td {:colspan "1", :rowspan "1"} "b1"]
  ;  [:td {:colspan "1", :rowspan "1"} "1011 0001"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "→ [empty]"]
  ;  [:td {:colspan "1", :rowspan "1"} "return void from method"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "saload"]
  ;  [:td {:colspan "1", :rowspan "1"} "35"]
  ;  [:td {:colspan "1", :rowspan "1"} "0011 0101"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index → value"]
  ;  [:td {:colspan "1", :rowspan "1"} "load short from array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "sastore"]
  ;  [:td {:colspan "1", :rowspan "1"} "56"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 0110"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "arrayref, index, value →"]
  ;  [:td {:colspan "1", :rowspan "1"} "store short to array"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "sipush"]
  ;  [:td {:colspan "1", :rowspan "1"} "11"]
  ;  [:td {:colspan "1", :rowspan "1"} "0001 0001"]
  ;  [:td {:colspan "1", :rowspan "1"} "2: byte1, byte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "→ value"]
  ;  [:td {:colspan "1", :rowspan "1"} "push a short onto the stack"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "swap"]
  ;  [:td {:colspan "1", :rowspan "1"} "5f"]
  ;  [:td {:colspan "1", :rowspan "1"} "0101 1111"]
  ;  [:td {:colspan "1", :rowspan "1"}]
  ;  [:td {:colspan "1", :rowspan "1"} "value2, value1 → value1, value2"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "swaps two top words on the stack (note that value1 and value2 must not be double or long)"]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "tableswitch"]
  ;  [:td {:colspan "1", :rowspan "1"} "aa"]
  ;  [:td {:colspan "1", :rowspan "1"} "1010 1010"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "4+: [0–3 bytes padding], defaultbyte1, defaultbyte2, defaultbyte3, defaultbyte4, lowbyte1, lowbyte2, lowbyte3, lowbyte4, highbyte1, highbyte2, highbyte3, highbyte4, jump offsets..."]
  ;  [:td {:colspan "1", :rowspan "1"} "index →"]
  ;  [:td {:colspan "1", :rowspan "1"} "continue execution from an address in the table at offset " [:i {} "index"]]]
  ; [:tr
  ;  {}
  ;  [:td {:colspan "1", :rowspan "1"} "wide"]
  ;  [:td {:colspan "1", :rowspan "1"} "c4"]
  ;  [:td {:colspan "1", :rowspan "1"} "1100 0100"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "3/5: opcode, indexbyte1, indexbyte2"
  ;   [:br {:clear "none"}]
  ;   "\nor"
  ;   [:br {:clear "none"}]
  ;   "\niinc, indexbyte1, indexbyte2, countbyte1, countbyte2"]
  ;  [:td {:colspan "1", :rowspan "1"} "[same as for corresponding instructions]"]
  ;  [:td
  ;   {:colspan "1", :rowspan "1"}
  ;   "execute "
  ;   [:i {} "opcode"]
  ;   ", where "
  ;   [:i {} "opcode"]
  ;   " is either iload, fload, aload, lload, dload, istore, fstore, astore, lstore, dstore, or ret, but assume the "
  ;   [:i {} "index"]
  ;   " is 16 bit; or execute iinc, where the "
  ;   [:i {} "index"]
  ;   " is 16 bits and the constant to increment by is a signed 16 bit short"]]])

(defn mk-table []
  (->>
    (map
      (fn [tr]
        [(keyword (last (nth tr 2)))
         (Integer/parseInt (last (nth tr 3)) 16)])
      (filter #(= :tr (first %)) (rest op-table)))
    (into (sorted-map))))
