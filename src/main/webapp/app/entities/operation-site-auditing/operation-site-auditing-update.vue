<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.operationSiteAuditing.home.createOrEditLabel"
          data-cy="OperationSiteAuditingCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="operationSiteAuditing.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="operationSiteAuditing.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.itemName')"
              for="operation-site-auditing-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="operation-site-auditing-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.cateGory')"
              for="operation-site-auditing-cateGory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="cateGory"
              id="operation-site-auditing-cateGory"
              data-cy="cateGory"
              :class="{ valid: !v$.cateGory.$invalid, invalid: v$.cateGory.$invalid }"
              v-model="v$.cateGory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.supplier')"
              for="operation-site-auditing-supplier"
            ></label>
            <input
              type="text"
              class="form-control"
              name="supplier"
              id="operation-site-auditing-supplier"
              data-cy="supplier"
              :class="{ valid: !v$.supplier.$invalid, invalid: v$.supplier.$invalid }"
              v-model="v$.supplier.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.operationSite')"
              for="operation-site-auditing-operationSite"
            ></label>
            <input
              type="text"
              class="form-control"
              name="operationSite"
              id="operation-site-auditing-operationSite"
              data-cy="operationSite"
              :class="{ valid: !v$.operationSite.$invalid, invalid: v$.operationSite.$invalid }"
              v-model="v$.operationSite.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.linkSupplierFactory')"
              for="operation-site-auditing-linkSupplierFactory"
            ></label>
            <input
              type="text"
              class="form-control"
              name="linkSupplierFactory"
              id="operation-site-auditing-linkSupplierFactory"
              data-cy="linkSupplierFactory"
              :class="{ valid: !v$.linkSupplierFactory.$invalid, invalid: v$.linkSupplierFactory.$invalid }"
              v-model="v$.linkSupplierFactory.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.typeOfSite')"
              for="operation-site-auditing-typeOfSite"
            ></label>
            <input
              type="text"
              class="form-control"
              name="typeOfSite"
              id="operation-site-auditing-typeOfSite"
              data-cy="typeOfSite"
              :class="{ valid: !v$.typeOfSite.$invalid, invalid: v$.typeOfSite.$invalid }"
              v-model="v$.typeOfSite.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.auditingTool')"
              for="operation-site-auditing-auditingTool"
            ></label>
            <input
              type="text"
              class="form-control"
              name="auditingTool"
              id="operation-site-auditing-auditingTool"
              data-cy="auditingTool"
              :class="{ valid: !v$.auditingTool.$invalid, invalid: v$.auditingTool.$invalid }"
              v-model="v$.auditingTool.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.auditingDate')"
              for="operation-site-auditing-auditingDate"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="operation-site-auditing-auditingDate"
                  v-model="v$.auditingDate.$model"
                  name="auditingDate"
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
                id="operation-site-auditing-auditingDate"
                data-cy="auditingDate"
                type="text"
                class="form-control"
                name="auditingDate"
                :class="{ valid: !v$.auditingDate.$invalid, invalid: v$.auditingDate.$invalid }"
                v-model="v$.auditingDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.csrResult')"
              for="operation-site-auditing-csrResult"
            ></label>
            <input
              type="text"
              class="form-control"
              name="csrResult"
              id="operation-site-auditing-csrResult"
              data-cy="csrResult"
              :class="{ valid: !v$.csrResult.$invalid, invalid: v$.csrResult.$invalid }"
              v-model="v$.csrResult.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.qualityProductionResult')"
              for="operation-site-auditing-qualityProductionResult"
            ></label>
            <input
              type="text"
              class="form-control"
              name="qualityProductionResult"
              id="operation-site-auditing-qualityProductionResult"
              data-cy="qualityProductionResult"
              :class="{ valid: !v$.qualityProductionResult.$invalid, invalid: v$.qualityProductionResult.$invalid }"
              v-model="v$.qualityProductionResult.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.businessLiabilityResult')"
              for="operation-site-auditing-businessLiabilityResult"
            ></label>
            <input
              type="text"
              class="form-control"
              name="businessLiabilityResult"
              id="operation-site-auditing-businessLiabilityResult"
              data-cy="businessLiabilityResult"
              :class="{ valid: !v$.businessLiabilityResult.$invalid, invalid: v$.businessLiabilityResult.$invalid }"
              v-model="v$.businessLiabilityResult.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.comments')"
              for="operation-site-auditing-comments"
            ></label>
            <input
              type="text"
              class="form-control"
              name="comments"
              id="operation-site-auditing-comments"
              data-cy="comments"
              :class="{ valid: !v$.comments.$invalid, invalid: v$.comments.$invalid }"
              v-model="v$.comments.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.issueDate')"
              for="operation-site-auditing-issueDate"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="operation-site-auditing-issueDate"
                  v-model="v$.issueDate.$model"
                  name="issueDate"
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
                id="operation-site-auditing-issueDate"
                data-cy="issueDate"
                type="text"
                class="form-control"
                name="issueDate"
                :class="{ valid: !v$.issueDate.$invalid, invalid: v$.issueDate.$invalid }"
                v-model="v$.issueDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.dueDate')"
              for="operation-site-auditing-dueDate"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="operation-site-auditing-dueDate"
                  v-model="v$.dueDate.$model"
                  name="dueDate"
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
                id="operation-site-auditing-dueDate"
                data-cy="dueDate"
                type="text"
                class="form-control"
                name="dueDate"
                :class="{ valid: !v$.dueDate.$invalid, invalid: v$.dueDate.$invalid }"
                v-model="v$.dueDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.closedDate')"
              for="operation-site-auditing-closedDate"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="operation-site-auditing-closedDate"
                  v-model="v$.closedDate.$model"
                  name="closedDate"
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
                id="operation-site-auditing-closedDate"
                data-cy="closedDate"
                type="text"
                class="form-control"
                name="closedDate"
                :class="{ valid: !v$.closedDate.$invalid, invalid: v$.closedDate.$invalid }"
                v-model="v$.closedDate.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.closed')"
              for="operation-site-auditing-closed"
            ></label>
            <input
              type="text"
              class="form-control"
              name="closed"
              id="operation-site-auditing-closed"
              data-cy="closed"
              :class="{ valid: !v$.closed.$invalid, invalid: v$.closed.$invalid }"
              v-model="v$.closed.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.status')"
              for="operation-site-auditing-status"
            ></label>
            <input
              type="text"
              class="form-control"
              name="status"
              id="operation-site-auditing-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.operationSiteAuditing.capComments')"
              for="operation-site-auditing-capComments"
            ></label>
            <input
              type="text"
              class="form-control"
              name="capComments"
              id="operation-site-auditing-capComments"
              data-cy="capComments"
              :class="{ valid: !v$.capComments.$invalid, invalid: v$.capComments.$invalid }"
              v-model="v$.capComments.$model"
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
<script lang="ts" src="./operation-site-auditing-update.component.ts"></script>
