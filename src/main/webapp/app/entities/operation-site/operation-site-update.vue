<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.operationSite.home.createOrEditLabel"
          data-cy="OperationSiteCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.operationSite.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="operationSite.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="operationSite.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.itemName')"
              for="operation-site-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="operation-site-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.linkSupplierFactory')"
              for="operation-site-linkSupplierFactory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="linkSupplierFactory"
              id="operation-site-linkSupplierFactory"
              data-cy="linkSupplierFactory"
              :class="{ valid: !v$.linkSupplierFactory.$invalid, invalid: v$.linkSupplierFactory.$invalid }"
              v-model="v$.linkSupplierFactory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.typeOfSite')"
              for="operation-site-typeOfSite"
            ></label>
            <input
              type="text"
              class="form-control"
              name="typeOfSite"
              id="operation-site-typeOfSite"
              data-cy="typeOfSite"
              :class="{ valid: !v$.typeOfSite.$invalid, invalid: v$.typeOfSite.$invalid }"
              v-model="v$.typeOfSite.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.contact')"
              for="operation-site-contact"
            ></label>
            <input
              type="text"
              class="form-control"
              name="contact"
              id="operation-site-contact"
              data-cy="contact"
              :class="{ valid: !v$.contact.$invalid, invalid: v$.contact.$invalid }"
              v-model="v$.contact.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.siteAddress')"
              for="operation-site-siteAddress"
            ></label>
            <input
              type="text"
              class="form-control"
              name="siteAddress"
              id="operation-site-siteAddress"
              data-cy="siteAddress"
              :class="{ valid: !v$.siteAddress.$invalid, invalid: v$.siteAddress.$invalid }"
              v-model="v$.siteAddress.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.cateGory')"
              for="operation-site-cateGory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cateGory"
              id="operation-site-cateGory"
              data-cy="cateGory"
              :class="{ valid: !v$.cateGory.$invalid, invalid: v$.cateGory.$invalid }"
              v-model="v$.cateGory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.country')"
              for="operation-site-country"
            ></label>
            <input
              type="text"
              class="form-control"
              name="country"
              id="operation-site-country"
              data-cy="country"
              :class="{ valid: !v$.country.$invalid, invalid: v$.country.$invalid }"
              v-model="v$.country.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.kingdeeId')"
              for="operation-site-kingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="operation-site-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.businessLicense')"
              for="operation-site-businessLicense"
            ></label>
            <input
              type="text"
              class="form-control"
              name="businessLicense"
              id="operation-site-businessLicense"
              data-cy="businessLicense"
              :class="{ valid: !v$.businessLicense.$invalid, invalid: v$.businessLicense.$invalid }"
              v-model="v$.businessLicense.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.sasDate')"
              for="operation-site-sasDate"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="operation-site-sasDate"
                  v-model="v$.sasDate.$model"
                  name="sasDate"
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
                id="operation-site-sasDate"
                data-cy="sasDate"
                type="text"
                class="form-control"
                name="sasDate"
                :class="{ valid: !v$.sasDate.$invalid, invalid: v$.sasDate.$invalid }"
                v-model="v$.sasDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.iso900ValidUtil')"
              for="operation-site-iso900ValidUtil"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="operation-site-iso900ValidUtil"
                  v-model="v$.iso900ValidUtil.$model"
                  name="iso900ValidUtil"
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
                id="operation-site-iso900ValidUtil"
                data-cy="iso900ValidUtil"
                type="text"
                class="form-control"
                name="iso900ValidUtil"
                :class="{ valid: !v$.iso900ValidUtil.$invalid, invalid: v$.iso900ValidUtil.$invalid }"
                v-model="v$.iso900ValidUtil.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.iso14001ValidUtil')"
              for="operation-site-iso14001ValidUtil"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="operation-site-iso14001ValidUtil"
                  v-model="v$.iso14001ValidUtil.$model"
                  name="iso14001ValidUtil"
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
                id="operation-site-iso14001ValidUtil"
                data-cy="iso14001ValidUtil"
                type="text"
                class="form-control"
                name="iso14001ValidUtil"
                :class="{ valid: !v$.iso14001ValidUtil.$invalid, invalid: v$.iso14001ValidUtil.$invalid }"
                v-model="v$.iso14001ValidUtil.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.attributeLor')"
              for="operation-site-attributeLor"
            ></label>
            <input
              type="text"
              class="form-control"
              name="attributeLor"
              id="operation-site-attributeLor"
              data-cy="attributeLor"
              :class="{ valid: !v$.attributeLor.$invalid, invalid: v$.attributeLor.$invalid }"
              v-model="v$.attributeLor.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.siteQualification')"
              for="operation-site-siteQualification"
            ></label>
            <input
              type="text"
              class="form-control"
              name="siteQualification"
              id="operation-site-siteQualification"
              data-cy="siteQualification"
              :class="{ valid: !v$.siteQualification.$invalid, invalid: v$.siteQualification.$invalid }"
              v-model="v$.siteQualification.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.qualificationScore')"
              for="operation-site-qualificationScore"
            ></label>
            <input
              type="text"
              class="form-control"
              name="qualificationScore"
              id="operation-site-qualificationScore"
              data-cy="qualificationScore"
              :class="{ valid: !v$.qualificationScore.$invalid, invalid: v$.qualificationScore.$invalid }"
              v-model="v$.qualificationScore.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.pqvScore')"
              for="operation-site-pqvScore"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pqvScore"
              id="operation-site-pqvScore"
              data-cy="pqvScore"
              :class="{ valid: !v$.pqvScore.$invalid, invalid: v$.pqvScore.$invalid }"
              v-model="v$.pqvScore.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.pqvDate')"
              for="operation-site-pqvDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pqvDate"
              id="operation-site-pqvDate"
              data-cy="pqvDate"
              :class="{ valid: !v$.pqvDate.$invalid, invalid: v$.pqvDate.$invalid }"
              v-model="v$.pqvDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.pqvDecision')"
              for="operation-site-pqvDecision"
            ></label>
            <input
              type="text"
              class="form-control"
              name="pqvDecision"
              id="operation-site-pqvDecision"
              data-cy="pqvDecision"
              :class="{ valid: !v$.pqvDecision.$invalid, invalid: v$.pqvDecision.$invalid }"
              v-model="v$.pqvDecision.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.technicalAuditDate')"
              for="operation-site-technicalAuditDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="technicalAuditDate"
              id="operation-site-technicalAuditDate"
              data-cy="technicalAuditDate"
              :class="{ valid: !v$.technicalAuditDate.$invalid, invalid: v$.technicalAuditDate.$invalid }"
              v-model="v$.technicalAuditDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.technicalAuditScore')"
              for="operation-site-technicalAuditScore"
            ></label>
            <input
              type="text"
              class="form-control"
              name="technicalAuditScore"
              id="operation-site-technicalAuditScore"
              data-cy="technicalAuditScore"
              :class="{ valid: !v$.technicalAuditScore.$invalid, invalid: v$.technicalAuditScore.$invalid }"
              v-model="v$.technicalAuditScore.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.thirdRdPartyDate')"
              for="operation-site-thirdRdPartyDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="thirdRdPartyDate"
              id="operation-site-thirdRdPartyDate"
              data-cy="thirdRdPartyDate"
              :class="{ valid: !v$.thirdRdPartyDate.$invalid, invalid: v$.thirdRdPartyDate.$invalid }"
              v-model="v$.thirdRdPartyDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.thirdRdPartyScore')"
              for="operation-site-thirdRdPartyScore"
            ></label>
            <input
              type="text"
              class="form-control"
              name="thirdRdPartyScore"
              id="operation-site-thirdRdPartyScore"
              data-cy="thirdRdPartyScore"
              :class="{ valid: !v$.thirdRdPartyScore.$invalid, invalid: v$.thirdRdPartyScore.$invalid }"
              v-model="v$.thirdRdPartyScore.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.bopeDate')"
              for="operation-site-bopeDate"
            ></label>
            <input
              type="text"
              class="form-control"
              name="bopeDate"
              id="operation-site-bopeDate"
              data-cy="bopeDate"
              :class="{ valid: !v$.bopeDate.$invalid, invalid: v$.bopeDate.$invalid }"
              v-model="v$.bopeDate.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.bopeScore')"
              for="operation-site-bopeScore"
            ></label>
            <input
              type="text"
              class="form-control"
              name="bopeScore"
              id="operation-site-bopeScore"
              data-cy="bopeScore"
              :class="{ valid: !v$.bopeScore.$invalid, invalid: v$.bopeScore.$invalid }"
              v-model="v$.bopeScore.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSite.capRequired')"
              for="operation-site-capRequired"
            ></label>
            <input
              type="text"
              class="form-control"
              name="capRequired"
              id="operation-site-capRequired"
              data-cy="capRequired"
              :class="{ valid: !v$.capRequired.$invalid, invalid: v$.capRequired.$invalid }"
              v-model="v$.capRequired.$model"
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
<script lang="ts" src="./operation-site-update.component.ts"></script>
