<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.quotation.home.createOrEditLabel"
          data-cy="QuotationCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.quotation.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="quotation.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="quotation.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.quotation.itemName')"
              for="quotation-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="quotation-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.quotation.quotationDate')"
              for="quotation-quotationDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="quotationDate"
              id="quotation-quotationDate"
              data-cy="quotationDate"
              :class="{ valid: !v$.quotationDate.$invalid, invalid: v$.quotationDate.$invalid }"
              v-model="v$.quotationDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.quotation.inquiryDocNumber')"
              for="quotation-inquiryDocNumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="inquiryDocNumber"
              id="quotation-inquiryDocNumber"
              data-cy="inquiryDocNumber"
              :class="{ valid: !v$.inquiryDocNumber.$invalid, invalid: v$.inquiryDocNumber.$invalid }"
              v-model="v$.inquiryDocNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.quotation.docNo')" for="quotation-docNo"></label>
            <input
              type="text"
              class="form-control"
              name="docNo"
              id="quotation-docNo"
              data-cy="docNo"
              :class="{ valid: !v$.docNo.$invalid, invalid: v$.docNo.$invalid }"
              v-model="v$.docNo.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.quotation.docStatus')"
              for="quotation-docStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="docStatus"
              id="quotation-docStatus"
              data-cy="docStatus"
              :class="{ valid: !v$.docStatus.$invalid, invalid: v$.docStatus.$invalid }"
              v-model="v$.docStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.quotation.supplier')"
              for="quotation-supplier"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplier"
              id="quotation-supplier"
              data-cy="supplier"
              :class="{ valid: !v$.supplier.$invalid, invalid: v$.supplier.$invalid }"
              v-model="v$.supplier.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.quotation.kingdeeId')"
              for="quotation-kingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="quotation-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
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
<script lang="ts" src="./quotation-update.component.ts"></script>
