<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.supplierQualification.home.createOrEditLabel"
          data-cy="SupplierQualificationCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.supplierQualification.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="supplierQualification.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="supplierQualification.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplierQualification.itemName')"
              for="supplier-qualification-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="supplier-qualification-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplierQualification.cateGory')"
              for="supplier-qualification-cateGory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cateGory"
              id="supplier-qualification-cateGory"
              data-cy="cateGory"
              :class="{ valid: !v$.cateGory.$invalid, invalid: v$.cateGory.$invalid }"
              v-model="v$.cateGory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplierQualification.supplier')"
              for="supplier-qualification-supplier"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplier"
              id="supplier-qualification-supplier"
              data-cy="supplier"
              :class="{ valid: !v$.supplier.$invalid, invalid: v$.supplier.$invalid }"
              v-model="v$.supplier.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplierQualification.date')"
              for="supplier-qualification-date"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="supplier-qualification-date"
                  v-model="v$.date.$model"
                  name="date"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="supplier-qualification-date"
                data-cy="date"
                type="text"
                class="form-control"
                name="date"
                :class="{ valid: !v$.date.$invalid, invalid: v$.date.$invalid }"
                v-model="v$.date.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplierQualification.supplierStatus')"
              for="supplier-qualification-supplierStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplierStatus"
              id="supplier-qualification-supplierStatus"
              data-cy="supplierStatus"
              :class="{ valid: !v$.supplierStatus.$invalid, invalid: v$.supplierStatus.$invalid }"
              v-model="v$.supplierStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplierQualification.evaluationStatus')"
              for="supplier-qualification-evaluationStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="evaluationStatus"
              id="supplier-qualification-evaluationStatus"
              data-cy="evaluationStatus"
              :class="{ valid: !v$.evaluationStatus.$invalid, invalid: v$.evaluationStatus.$invalid }"
              v-model="v$.evaluationStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplierQualification.businessLiabilityBopeScore')"
              for="supplier-qualification-businessLiabilityBopeScore"
            ></label>
            <input
              type="text"
              class="form-control"
              name="businessLiabilityBopeScore"
              id="supplier-qualification-businessLiabilityBopeScore"
              data-cy="businessLiabilityBopeScore"
              :class="{ valid: !v$.businessLiabilityBopeScore.$invalid, invalid: v$.businessLiabilityBopeScore.$invalid }"
              v-model="v$.businessLiabilityBopeScore.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.supplierQualification.comments')"
              for="supplier-qualification-comments"
            ></label>
            <input
              type="text"
              class="form-control"
              name="comments"
              id="supplier-qualification-comments"
              data-cy="comments"
              :class="{ valid: !v$.comments.$invalid, invalid: v$.comments.$invalid }"
              v-model="v$.comments.$model"
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
<script lang="ts" src="./supplier-qualification-update.component.ts"></script>
