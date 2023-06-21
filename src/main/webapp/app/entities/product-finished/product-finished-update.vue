<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.productFinished.home.createOrEditLabel"
          data-cy="ProductFinishedCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.productFinished.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="productFinished.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="productFinished.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productFinished.itemName')"
              for="product-finished-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="product-finished-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productFinished.kingdeeId')"
              for="product-finished-kingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="product-finished-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productFinished.supplier')"
              for="product-finished-supplier"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplier"
              id="product-finished-supplier"
              data-cy="supplier"
              :class="{ valid: !v$.supplier.$invalid, invalid: v$.supplier.$invalid }"
              v-model="v$.supplier.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productFinished.supplierEmail')"
              for="product-finished-supplierEmail"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplierEmail"
              id="product-finished-supplierEmail"
              data-cy="supplierEmail"
              :class="{ valid: !v$.supplierEmail.$invalid, invalid: v$.supplierEmail.$invalid }"
              v-model="v$.supplierEmail.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productFinished.orderDate')"
              for="product-finished-orderDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="orderDate"
              id="product-finished-orderDate"
              data-cy="orderDate"
              :class="{ valid: !v$.orderDate.$invalid, invalid: v$.orderDate.$invalid }"
              v-model="v$.orderDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productFinished.cateGory')"
              for="product-finished-cateGory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cateGory"
              id="product-finished-cateGory"
              data-cy="cateGory"
              :class="{ valid: !v$.cateGory.$invalid, invalid: v$.cateGory.$invalid }"
              v-model="v$.cateGory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productFinished.remark')"
              for="product-finished-remark"
            ></label>
            <input
              type="text"
              class="form-control"
              name="remark"
              id="product-finished-remark"
              data-cy="remark"
              :class="{ valid: !v$.remark.$invalid, invalid: v$.remark.$invalid }"
              v-model="v$.remark.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productFinished.materialReceiptDate')"
              for="product-finished-materialReceiptDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="materialReceiptDate"
              id="product-finished-materialReceiptDate"
              data-cy="materialReceiptDate"
              :class="{ valid: !v$.materialReceiptDate.$invalid, invalid: v$.materialReceiptDate.$invalid }"
              v-model="v$.materialReceiptDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productFinished.docStatus')"
              for="product-finished-docStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="docStatus"
              id="product-finished-docStatus"
              data-cy="docStatus"
              :class="{ valid: !v$.docStatus.$invalid, invalid: v$.docStatus.$invalid }"
              v-model="v$.docStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.productFinished.supplierName')"
              for="product-finished-supplierName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplierName"
              id="product-finished-supplierName"
              data-cy="supplierName"
              :class="{ valid: !v$.supplierName.$invalid, invalid: v$.supplierName.$invalid }"
              v-model="v$.supplierName.$model"
            />
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.cancel')"></span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="v$.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.save')"></span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./product-finished-update.component.ts"></script>
