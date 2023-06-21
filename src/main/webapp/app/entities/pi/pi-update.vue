<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.pI.home.createOrEditLabel"
          data-cy="PICreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.pI.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="pI.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="pI.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.itemName')" for="pi-itemName"></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="pi-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.poNumber')" for="pi-poNumber"></label>
            <input
              type="text"
              class="form-control"
              name="poNumber"
              id="pi-poNumber"
              data-cy="poNumber"
              :class="{ valid: !v$.poNumber.$invalid, invalid: v$.poNumber.$invalid }"
              v-model="v$.poNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.category')" for="pi-category"></label>
            <input
              type="text"
              class="form-control"
              name="category"
              id="pi-category"
              data-cy="category"
              :class="{ valid: !v$.category.$invalid, invalid: v$.category.$invalid }"
              v-model="v$.category.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.client')" for="pi-client"></label>
            <input
              type="text"
              class="form-control"
              name="client"
              id="pi-client"
              data-cy="client"
              :class="{ valid: !v$.client.$invalid, invalid: v$.client.$invalid }"
              v-model="v$.client.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.orderDate')" for="pi-orderDate"></label>
            <input
              type="text"
              class="form-control"
              name="orderDate"
              id="pi-orderDate"
              data-cy="orderDate"
              :class="{ valid: !v$.orderDate.$invalid, invalid: v$.orderDate.$invalid }"
              v-model="v$.orderDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.pI.requestedEndOfProdDate')"
              for="pi-requestedEndOfProdDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="requestedEndOfProdDate"
              id="pi-requestedEndOfProdDate"
              data-cy="requestedEndOfProdDate"
              :class="{ valid: !v$.requestedEndOfProdDate.$invalid, invalid: v$.requestedEndOfProdDate.$invalid }"
              v-model="v$.requestedEndOfProdDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.pI.countryOfOrigin')"
              for="pi-countryOfOrigin"
            ></label>
            <input
              type="text"
              class="form-control"
              name="countryOfOrigin"
              id="pi-countryOfOrigin"
              data-cy="countryOfOrigin"
              :class="{ valid: !v$.countryOfOrigin.$invalid, invalid: v$.countryOfOrigin.$invalid }"
              v-model="v$.countryOfOrigin.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.pI.countryOfFinalDestination')"
              for="pi-countryOfFinalDestination"
            ></label>
            <input
              type="text"
              class="form-control"
              name="countryOfFinalDestination"
              id="pi-countryOfFinalDestination"
              data-cy="countryOfFinalDestination"
              :class="{ valid: !v$.countryOfFinalDestination.$invalid, invalid: v$.countryOfFinalDestination.$invalid }"
              v-model="v$.countryOfFinalDestination.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.consignee')" for="pi-consignee"></label>
            <input
              type="text"
              class="form-control"
              name="consignee"
              id="pi-consignee"
              data-cy="consignee"
              :class="{ valid: !v$.consignee.$invalid, invalid: v$.consignee.$invalid }"
              v-model="v$.consignee.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.carriageBy')" for="pi-carriageBy"></label>
            <input
              type="text"
              class="form-control"
              name="carriageBy"
              id="pi-carriageBy"
              data-cy="carriageBy"
              :class="{ valid: !v$.carriageBy.$invalid, invalid: v$.carriageBy.$invalid }"
              v-model="v$.carriageBy.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.pI.termsOfDelivery')"
              for="pi-termsOfDelivery"
            ></label>
            <input
              type="text"
              class="form-control"
              name="termsOfDelivery"
              id="pi-termsOfDelivery"
              data-cy="termsOfDelivery"
              :class="{ valid: !v$.termsOfDelivery.$invalid, invalid: v$.termsOfDelivery.$invalid }"
              v-model="v$.termsOfDelivery.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.termsOfPayment')" for="pi-termsOfPayment"></label>
            <input
              type="text"
              class="form-control"
              name="termsOfPayment"
              id="pi-termsOfPayment"
              data-cy="termsOfPayment"
              :class="{ valid: !v$.termsOfPayment.$invalid, invalid: v$.termsOfPayment.$invalid }"
              v-model="v$.termsOfPayment.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.currency')" for="pi-currency"></label>
            <input
              type="text"
              class="form-control"
              name="currency"
              id="pi-currency"
              data-cy="currency"
              :class="{ valid: !v$.currency.$invalid, invalid: v$.currency.$invalid }"
              v-model="v$.currency.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.remarks')" for="pi-remarks"></label>
            <input
              type="text"
              class="form-control"
              name="remarks"
              id="pi-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.kingdeeId')" for="pi-kingdeeId"></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="pi-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.pI.portOfDischarge')"
              for="pi-portOfDischarge"
            ></label>
            <input
              type="text"
              class="form-control"
              name="portOfDischarge"
              id="pi-portOfDischarge"
              data-cy="portOfDischarge"
              :class="{ valid: !v$.portOfDischarge.$invalid, invalid: v$.portOfDischarge.$invalid }"
              v-model="v$.portOfDischarge.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.portOfLoading')" for="pi-portOfLoading"></label>
            <input
              type="text"
              class="form-control"
              name="portOfLoading"
              id="pi-portOfLoading"
              data-cy="portOfLoading"
              :class="{ valid: !v$.portOfLoading.$invalid, invalid: v$.portOfLoading.$invalid }"
              v-model="v$.portOfLoading.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.pI.docStatus')" for="pi-docStatus"></label>
            <input
              type="text"
              class="form-control"
              name="docStatus"
              id="pi-docStatus"
              data-cy="docStatus"
              :class="{ valid: !v$.docStatus.$invalid, invalid: v$.docStatus.$invalid }"
              v-model="v$.docStatus.$model"
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
<script lang="ts" src="./pi-update.component.ts"></script>
