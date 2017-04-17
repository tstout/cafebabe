(ns cafebabe.class-codec
  (:require [gloss.core :refer [defcodec
                                enum
                                string
                                repeated
                                header
                                prefix
                                finite-frame]]))

;;
;; Look here for more examples:
;; https://github.com/d-t-w/by-example-gloss/blob/master/src/by_example_gloss/core.clj


; From https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html
;
;ClassFile {
;           u4             magic;
;           u2             minor_version;
;           u2             major_version;
;           u2             constant_pool_count;
;           cp_info        constant_pool[constant_pool_count-1];
;                          u2             access_flags;
;                          u2             this_class;
;                          u2             super_class;
;                          u2             interfaces_count;
;                          u2             interfaces[interfaces_count];
;           u2             fields_count;
;           field_info     fields[fields_count];
;                          u2             methods_count;
;                          method_info    methods[methods_count];
;           u2             attributes_count;
;           attribute_info attributes[attributes_count];
;           }

(defcodec class-hdr
          [:magic :uint32
           :minor-version :uint16
           :major-version :uint16])

(defcodec constant-type
          (enum :ubyte
                {:c-class                7
                 :c-field-ref            9
                 :c-method-ref           10
                 :c-interface-method-ref 11
                 :c-string               8
                 :c-integer              3
                 :c-float                4
                 :c-long                 5
                 :c-double               6
                 :c-name-and-type        12
                 :c-utf8                 1
                 :c-method-handle        15
                 :c-method-type          16
                 :c-invoke-dynamic       18}))

;CONSTANT_Class_info {
;                     u1 tag;
;                     u2 name_index;
;                     }
(defcodec class-info
          {:constant-type :c-class
           :name-index    :uint16})

;CONSTANT_Fieldref_info {
;                        u1 tag;
;                        u2 class_index;
;                        u2 name_and_type_index;
;                        }
(defcodec field-ref-info
          {:constant-type       :c-field-ref
           :class-index         :uint16
           :name-and-type-index :uint16})

;CONSTANT_Methodref_info {
;                         u1 tag;
;                         u2 class_index;
;                         u2 name_and_type_index;
;                         }

(defcodec method-ref-info
          {:constant-type       :c-method-ref
           :class-index         :uint16
           :name-and-type-index :uint16})

;CONSTANT_InterfaceMethodref_info {
;                                  u1 tag;
;                                  u2 class_index;
;                                  u2 name_and_type_index;
;                                  }
(defcodec interface-method-ref-info
          {:constant-type       :c-interface-method-ref
           :class-index         :uint16
           :name-and-type-index :uint16})

;CONSTANT_String_info {
;                      u1 tag;
;                      u2 string_index;
;                      }
(defcodec string-info
          {:constant-type :c-string
           :string-index  :uint16})

;CONSTANT_Integer_info {
;                       u1 tag;
;                       u4 bytes;
;                       }
(defcodec integer-info
          {:constant-type :c-integer
           :bytes         :uint32})

;CONSTANT_Float_info {
;                     u1 tag;
;                     u4 bytes;
;                     }
(defcodec float-info
          {:constant-type :c-float
           :bytes         :uint32})

;CONSTANT_Long_info {
;                    u1 tag                                  ;
;                    u4 high_bytes                           ;
;                    u4 low_bytes                            ;
;                    }
(defcodec long-info
          {:constant-type :c-long
           :high-bytes    :uint32
           :low-bytes     :uint32})

;CONSTANT_Double_info {
;                      u1 tag;
;                      u4 high_bytes;
;                      u4 low_bytes;
;                      }
(defcodec double-info
          {:constant-type :c-double
           :high-bytes    :uint32})

;CONSTANT_NameAndType_info {
;                           u1 tag;
;                           u2 name_index;
;                           u2 descriptor_index;
;                           }
(defcodec name-and-type-info
          {:constant-type    :c-name-and-type
           :name-index       :uint16
           :descriptor-index :uint16})

;CONSTANT_Utf8_info {
;                    u1 tag;
;                    u2 length;
;                    u1 bytes[length];
;                    }
(defcodec utf8-info
          {:constant-type :c-utf8
           :str           (finite-frame :uint16 (string :utf8))})

;CONSTANT_MethodHandle_info {
;                            u1 tag;
;                            u1 reference_kind;
;                            u2 reference_index;
;                            }
(defcodec method-handle-info
          {:constant-type   :c-method-handle
           :reference-kind  :ubyte
           :reference-index :uint16})

;CONSTANT_MethodType_info {
;                          u1 tag;
;                          u2 descriptor_index;
;                          }
(defcodec method-type-info
          {:constant-type    :c-method-type
           :descriptor-index :uint16})

;CONSTANT_InvokeDynamic_info {
;                             u1 tag;
;                             u2 bootstrap_method_attr_index;
;                             u2 name_and_type_index;
;                             }
(defcodec invoke-dynamic-info
          {:constant-type               :c-invoke-dynamic
           :bootstrap-method-attr-index :uint16
           :name-and-type-index         :uint16})


(defcodec constant-pool
          (header constant-type
                  {:c-class                class-info
                   :c-field-ref            field-ref-info
                   :c-method-ref           method-ref-info
                   :c-interface-method-ref interface-method-ref-info
                   :c-string               string-info
                   :c-integer              integer-info
                   :c-float                float-info
                   :c-long                 long-info
                   :c-double               double-info
                   :c-name-and-type        name-and-type-info
                   :c-utf8                 utf8-info
                   :c-method-handle        method-handle-info
                   :c-method-type          method-type-info
                   :c-invoke-dynamic       invoke-dynamic-info}
                  :constant-type))

(defcodec cp-info
          (repeated constant-pool :prefix (prefix :uint16 dec inc)))


(defcodec class-codec
          [class-hdr
           cp-info])