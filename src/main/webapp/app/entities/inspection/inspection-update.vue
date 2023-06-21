<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.inspection.home.createOrEditLabel"
          data-cy="InspectionCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.inspection.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="inspection.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="inspection.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.inspection.itemName')"
              for="inspection-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="inspection-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.inspection.kingdeeId')"
              for="inspection-kingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="inspection-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.inspection.supplierName')"
              for="inspection-supplierName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplierName"
              id="inspection-supplierName"
              data-cy="supplierName"
              :class="{ valid: !v$.supplierName.$invalid, invalid: v$.supplierName.$invalid }"
              v-model="v$.supplierName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.inspection.email')" for="inspection-email"></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="inspection-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.inspection.inspectionDate')"
              for="inspection-inspectionDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="inspectionDate"
              id="inspection-inspectionDate"
              data-cy="inspectionDate"
              :class="{ valid: !v$.inspectionDate.$invalid, invalid: v$.inspectionDate.$invalid }"
              v-model="v$.inspectionDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.inspection.cateGory')"
              for="inspection-cateGory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cateGory"
              id="inspection-cateGory"
              data-cy="cateGory"
              :class="{ valid: !v$.cateGory.$invalid, invalid: v$.cateGory.$invalid }"
              v-model="v$.cateGory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.inspection.qCResult')"
              for="inspection-qCResult"
            ></label>
            <input
              type="text"
              class="form-control"
              name="qCResult"
              id="inspection-qCResult"
              data-cy="qCResult"
              :class="{ valid: !v$.qCResult.$invalid, invalid: v$.qCResult.$invalid }"
              v-model="v$.qCResult.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.inspection.docStatus')"
              for="inspection-docStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="docStatus"
              id="inspection-docStatus"
              data-cy="docStatus"
              :class="{ valid: !v$.docStatus.$invalid, invalid: v$.docStatus.$invalid }"
              v-model="v$.docStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.inspection.inspectionType')"
              for="inspection-inspectionType"
            ></label>
            <input
              type="text"
              class="form-control"
              name="inspectionType"
              id="inspection-inspectionType"
              data-cy="inspectionType"
              :class="{ valid: !v$.inspectionType.$invalid, invalid: v$.inspectionType.$invalid }"
              v-model="v$.inspectionType.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.inspection.inspectionBookingStatus')"
              for="inspection-inspectionBookingStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="inspectionBookingStatus"
              id="inspection-inspectionBookingStatus"
              data-cy="inspectionBookingStatus"
              :class="{ valid: !v$.inspectionBookingStatus.$invalid, invalid: v$.inspectionBookingStatus.$invalid }"
              v-model="v$.inspectionBookingStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.inspection.inspectionEndDate')"
              for="inspection-inspectionEndDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="inspectionEndDate"
              id="inspection-inspectionEndDate"
              data-cy="inspectionEndDate"
              :class="{ valid: !v$.inspectionEndDate.$invalid, invalid: v$.inspectionEndDate.$invalid }"
              v-model="v$.inspectionEndDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.inspection.supplierId')"
              for="inspection-supplierId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplierId"
              id="inspection-supplierId"
              data-cy="supplierId"
              :class="{ valid: !v$.supplierId.$invalid, invalid: v$.supplierId.$invalid }"
              v-model="v$.supplierId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.inspection.reportNumber')"
              for="inspection-reportNumber"
            ></label>
            <input
              type="text"
              class="form-control"
              name="reportNumber"
              id="inspection-reportNumber"
              data-cy="reportNumber"
              :class="{ valid: !v$.reportNumber.$invalid, invalid: v$.reportNumber.$invalid }"
              v-model="v$.reportNumber.$model"
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
<script lang="ts" src="./inspection-update.component.ts"></script>
