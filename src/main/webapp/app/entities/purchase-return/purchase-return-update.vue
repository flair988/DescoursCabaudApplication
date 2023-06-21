<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.purchaseReturn.home.createOrEditLabel"
          data-cy="PurchaseReturnCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.purchaseReturn.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="purchaseReturn.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="purchaseReturn.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.purchaseReturn.itemName')"
              for="purchase-return-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="purchase-return-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.purchaseReturn.kingdeeId')"
              for="purchase-return-kingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="purchase-return-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.purchaseReturn.docNumber')"
              for="purchase-return-docNumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="docNumber"
              id="purchase-return-docNumber"
              data-cy="docNumber"
              :class="{ valid: !v$.docNumber.$invalid, invalid: v$.docNumber.$invalid }"
              v-model="v$.docNumber.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.purchaseReturn.supplier')"
              for="purchase-return-supplier"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplier"
              id="purchase-return-supplier"
              data-cy="supplier"
              :class="{ valid: !v$.supplier.$invalid, invalid: v$.supplier.$invalid }"
              v-model="v$.supplier.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.purchaseReturn.supplierEmail')"
              for="purchase-return-supplierEmail"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplierEmail"
              id="purchase-return-supplierEmail"
              data-cy="supplierEmail"
              :class="{ valid: !v$.supplierEmail.$invalid, invalid: v$.supplierEmail.$invalid }"
              v-model="v$.supplierEmail.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.purchaseReturn.materialReturnDate')"
              for="purchase-return-materialReturnDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="materialReturnDate"
              id="purchase-return-materialReturnDate"
              data-cy="materialReturnDate"
              :class="{ valid: !v$.materialReturnDate.$invalid, invalid: v$.materialReturnDate.$invalid }"
              v-model="v$.materialReturnDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.purchaseReturn.reasonForReturn')"
              for="purchase-return-reasonForReturn"
            ></label>
            <input
              type="text"
              class="form-control"
              name="reasonForReturn"
              id="purchase-return-reasonForReturn"
              data-cy="reasonForReturn"
              :class="{ valid: !v$.reasonForReturn.$invalid, invalid: v$.reasonForReturn.$invalid }"
              v-model="v$.reasonForReturn.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.purchaseReturn.purchaseDept')"
              for="purchase-return-purchaseDept"
            ></label>
            <input
              type="text"
              class="form-control"
              name="purchaseDept"
              id="purchase-return-purchaseDept"
              data-cy="purchaseDept"
              :class="{ valid: !v$.purchaseDept.$invalid, invalid: v$.purchaseDept.$invalid }"
              v-model="v$.purchaseDept.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.purchaseReturn.remarks')"
              for="purchase-return-remarks"
            ></label>
            <input
              type="text"
              class="form-control"
              name="remarks"
              id="purchase-return-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.purchaseReturn.docStatus')"
              for="purchase-return-docStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="docStatus"
              id="purchase-return-docStatus"
              data-cy="docStatus"
              :class="{ valid: !v$.docStatus.$invalid, invalid: v$.docStatus.$invalid }"
              v-model="v$.docStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.purchaseReturn.itemCode')"
              for="purchase-return-itemCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemCode"
              id="purchase-return-itemCode"
              data-cy="itemCode"
              :class="{ valid: !v$.itemCode.$invalid, invalid: v$.itemCode.$invalid }"
              v-model="v$.itemCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.purchaseReturn.supplierCode')"
              for="purchase-return-supplierCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplierCode"
              id="purchase-return-supplierCode"
              data-cy="supplierCode"
              :class="{ valid: !v$.supplierCode.$invalid, invalid: v$.supplierCode.$invalid }"
              v-model="v$.supplierCode.$model"
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
<script lang="ts" src="./purchase-return-update.component.ts"></script>
