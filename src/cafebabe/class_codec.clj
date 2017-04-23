(ns cafebabe.class-codec
  "Define a gloss codec for reading/writing java class files.

  From https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html

  ClassFile {
             u4             magic;
             u2             minor_version;
             u2             major_version;
             u2             constant_pool_count;
             cp_info        constant_pool[constant_pool_count-1];
             u2             access_flags;
             u2             this_class;
             u2             super_class;
             u2             interfaces_count;
             u2             interfaces[interfaces_count];
             u2             fields_count;
             field_info     fields[fields_count];
             u2             methods_count;
             method_info    methods[methods_count];
             u2             attributes_count;
             attribute_info attributes[attributes_count];
             }

   Look here for examples regarding usage of gloss:
   https://github.com/d-t-w/by-example-gloss/blob/master/src/by_example_gloss/core.clj
   "
  (:require [gloss.core :refer [defcodec
                                enum
                                string
                                repeated
                                header
                                prefix
                                finite-frame
                                ordered-map]]))


(defcodec class-hdr
          {:magic         :uint32
           :minor-version :uint16
           :major-version :uint16})

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

(defcodec class-info
          {:constant-type :c-class
           :name-index    :uint16})


(defcodec field-ref-info
          {:constant-type       :c-field-ref
           :class-index         :uint16
           :name-and-type-index :uint16})

(defcodec method-ref-info
          (ordered-map
            :constant-type :c-method-ref
            :class-index :uint16
            :name-and-type-index :uint16))

(defcodec interface-method-ref-info
          {:constant-type       :c-interface-method-ref
           :class-index         :uint16
           :name-and-type-index :uint16})

(defcodec string-info
          {:constant-type :c-string
           :string-index  :uint16})

(defcodec integer-info
          {:constant-type :c-integer
           :bytes         :uint32})

(defcodec float-info
          {:constant-type :c-float
           :bytes         :uint32})

(defcodec long-info
          {:constant-type :c-long
           :high-bytes    :uint32
           :low-bytes     :uint32})

(defcodec double-info
          {:constant-type :c-double
           :high-bytes    :uint32})

(defcodec name-and-type-info
          {:constant-type    :c-name-and-type
           :name-index       :uint16
           :descriptor-index :uint16})

(defcodec utf8-info
          {:constant-type :c-utf8
           :str           (finite-frame :uint16 (string :utf8))})

(defcodec method-handle-info
          {:constant-type   :c-method-handle
           :reference-kind  :ubyte
           :reference-index :uint16})

(defcodec method-type-info
          {:constant-type    :c-method-type
           :descriptor-index :uint16})

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


;; This be a vector when empty?
(defcodec interfaces
          (repeated :uint16 :prefix :uint16))


(defcodec attribute-info
          {
           :attribute-name-index :uint16
           :info                 (repeated :ubyte :prefix :uint32)})


(defcodec attributes
          (repeated attribute-info :prefix :uint16))

(defcodec field-info
          (ordered-map
            :access-flags :uint16
            :name-index :uint16
            :descriptor-index :uint16
            :attributes attributes))

(defcodec fields
          (repeated field-info :prefix :uint16))

(defcodec method-info
          (ordered-map
            :access-flags :uint16
            :name-index :uint16
            :descriptor-index :uint16
            :attributes attributes))

(defcodec c-methods
          (repeated method-info :prefix :uint16))


(defcodec exception-table
          (ordered-map
            :start-pc :uint16
            :end-pc :uint16
            :handler-pc :unt16
            :catch-type :uint16))

;Code_attribute {
;                u2 attribute_name_index;
;                u4 attribute_length;
;                u2 max_stack;
;                u2 max_locals;
;                u4 code_length;
;                u1 code[code_length];
;                   u2 exception_table_length;
;                   {   u2 start_pc;
;                    u2 end_pc;
;                    u2 handler_pc;
;                    u2 catch_type;
;                    } exception_table[exception_table_length];
;                u2 attributes_count;
;                attribute_info attributes[attributes_count];
;                }
(defcodec code-attribute
          (ordered-map
            :attribute-name-index :uint16
            :attribute-length :uint32
            :max-stack :uint16
            :max-locals :uint16))
            ;;:code (repeated :ubyte)))
            ;:exception-table (repeated exception-table :prefix :uint16)
            ;:attributes attributes))


(defcodec class-codec
          (ordered-map
            :header class-hdr
            :constant-pool cp-info
            :access-flags :uint16
            :this-class :uint16
            :super-class :uint16
            :interfaces interfaces
            :fields fields
            :methods c-methods
            :attributes attributes))

(defcodec raw-bytes
          (repeated :ubyte :prefix :uint16))