<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.oPSiteQualification.home.createOrEditLabel"
          data-cy="OPSiteQualificationCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="oPSiteQualification.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="oPSiteQualification.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.itemName')"
              for="op-site-qualification-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="op-site-qualification-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.cateGory')"
              for="op-site-qualification-cateGory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cateGory"
              id="op-site-qualification-cateGory"
              data-cy="cateGory"
              :class="{ valid: !v$.cateGory.$invalid, invalid: v$.cateGory.$invalid }"
              v-model="v$.cateGory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.supplier')"
              for="op-site-qualification-supplier"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplier"
              id="op-site-qualification-supplier"
              data-cy="supplier"
              :class="{ valid: !v$.supplier.$invalid, invalid: v$.supplier.$invalid }"
              v-model="v$.supplier.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.date')"
              for="op-site-qualification-date"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="op-site-qualification-date"
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
                id="op-site-qualification-date"
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
              v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.operationSite')"
              for="op-site-qualification-operationSite"
            ></label>
            <input
              type="text"
              class="form-control"
              name="operationSite"
              id="op-site-qualification-operationSite"
              data-cy="operationSite"
              :class="{ valid: !v$.operationSite.$invalid, invalid: v$.operationSite.$invalid }"
              v-model="v$.operationSite.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.attributedLoRForThisSite')"
              for="op-site-qualification-attributedLoRForThisSite"
            ></label>
            <input
              type="text"
              class="form-control"
              name="attributedLoRForThisSite"
              id="op-site-qualification-attributedLoRForThisSite"
              data-cy="attributedLoRForThisSite"
              :class="{ valid: !v$.attributedLoRForThisSite.$invalid, invalid: v$.attributedLoRForThisSite.$invalid }"
              v-model="v$.attributedLoRForThisSite.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.siteQualificationStatus')"
              for="op-site-qualification-siteQualificationStatus"
            ></label>
            <input
              type="text"
              class="form-control"
              name="siteQualificationStatus"
              id="op-site-qualification-siteQualificationStatus"
              data-cy="siteQualificationStatus"
              :class="{ valid: !v$.siteQualificationStatus.$invalid, invalid: v$.siteQualificationStatus.$invalid }"
              v-model="v$.siteQualificationStatus.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.csrResult')"
              for="op-site-qualification-csrResult"
            ></label>
            <input
              type="text"
              class="form-control"
              name="csrResult"
              id="op-site-qualification-csrResult"
              data-cy="csrResult"
              :class="{ valid: !v$.csrResult.$invalid, invalid: v$.csrResult.$invalid }"
              v-model="v$.csrResult.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.qualityProductionResult')"
              for="op-site-qualification-qualityProductionResult"
            ></label>
            <input
              type="text"
              class="form-control"
              name="qualityProductionResult"
              id="op-site-qualification-qualityProductionResult"
              data-cy="qualityProductionResult"
              :class="{ valid: !v$.qualityProductionResult.$invalid, invalid: v$.qualityProductionResult.$invalid }"
              v-model="v$.qualityProductionResult.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.businessLiabilityResult')"
              for="op-site-qualification-businessLiabilityResult"
            ></label>
            <input
              type="text"
              class="form-control"
              name="businessLiabilityResult"
              id="op-site-qualification-businessLiabilityResult"
              data-cy="businessLiabilityResult"
              :class="{ valid: !v$.businessLiabilityResult.$invalid, invalid: v$.businessLiabilityResult.$invalid }"
              v-model="v$.businessLiabilityResult.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.oPSiteQualification.comments')"
              for="op-site-qualification-comments"
            ></label>
            <input
              type="text"
              class="form-control"
              name="comments"
              id="op-site-qualification-comments"
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
<script lang="ts" src="./op-site-qualification-update.component.ts"></script>
