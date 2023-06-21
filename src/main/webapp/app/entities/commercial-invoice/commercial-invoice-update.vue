<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.commercialInvoice.home.createOrEditLabel"
          data-cy="CommercialInvoiceCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.commercialInvoice.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="commercialInvoice.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="commercialInvoice.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.commercialInvoice.itemName')"
              for="commercial-invoice-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="commercial-invoice-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.commercialInvoice.kingdeeId')"
              for="commercial-invoice-kingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="commercial-invoice-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.commercialInvoice.date')"
              for="commercial-invoice-date"
            ></label>
            <input
              type="text"
              class="form-control"
              name="date"
              id="commercial-invoice-date"
              data-cy="date"
              :class="{ valid: !v$.date.$invalid, invalid: v$.date.$invalid }"
              v-model="v$.date.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.commercialInvoice.client')"
              for="commercial-invoice-client"
            ></label>
            <input
              type="text"
              class="form-control"
              name="client"
              id="commercial-invoice-client"
              data-cy="client"
              :class="{ valid: !v$.client.$invalid, invalid: v$.client.$invalid }"
              v-model="v$.client.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.commercialInvoice.cateGory')"
              for="commercial-invoice-cateGory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cateGory"
              id="commercial-invoice-cateGory"
              data-cy="cateGory"
              :class="{ valid: !v$.cateGory.$invalid, invalid: v$.cateGory.$invalid }"
              v-model="v$.cateGory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.commercialInvoice.totalPrice')"
              for="commercial-invoice-totalPrice"
            ></label>
            <input
              type="text"
              class="form-control"
              name="totalPrice"
              id="commercial-invoice-totalPrice"
              data-cy="totalPrice"
              :class="{ valid: !v$.totalPrice.$invalid, invalid: v$.totalPrice.$invalid }"
              v-model="v$.totalPrice.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.commercialInvoice.currency')"
              for="commercial-invoice-currency"
            ></label>
            <input
              type="text"
              class="form-control"
              name="currency"
              id="commercial-invoice-currency"
              data-cy="currency"
              :class="{ valid: !v$.currency.$invalid, invalid: v$.currency.$invalid }"
              v-model="v$.currency.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.commercialInvoice.remarks')"
              for="commercial-invoice-remarks"
            ></label>
            <input
              type="text"
              class="form-control"
              name="remarks"
              id="commercial-invoice-remarks"
              data-cy="remarks"
              :class="{ valid: !v$.remarks.$invalid, invalid: v$.remarks.$invalid }"
              v-model="v$.remarks.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.commercialInvoice.clientId')"
              for="commercial-invoice-clientId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="clientId"
              id="commercial-invoice-clientId"
              data-cy="clientId"
              :class="{ valid: !v$.clientId.$invalid, invalid: v$.clientId.$invalid }"
              v-model="v$.clientId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.commercialInvoice.docStatus')"
              for="commercial-invoice-docStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="docStatus"
              id="commercial-invoice-docStatus"
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
<script lang="ts" src="./commercial-invoice-update.component.ts"></script>
