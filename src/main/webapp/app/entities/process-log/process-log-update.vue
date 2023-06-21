<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.processLog.home.createOrEditLabel"
          data-cy="ProcessLogCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.processLog.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="processLog.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="processLog.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.processLog.type')" for="process-log-type"></label>
            <input
              type="text"
              class="form-control"
              name="type"
              id="process-log-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.processLog.request')"
              for="process-log-request"
            ></label>
            <input
              type="text"
              class="form-control"
              name="request"
              id="process-log-request"
              data-cy="request"
              :class="{ valid: !v$.request.$invalid, invalid: v$.request.$invalid }"
              v-model="v$.request.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.processLog.response')"
              for="process-log-response"
            ></label>
            <input
              type="text"
              class="form-control"
              name="response"
              id="process-log-response"
              data-cy="response"
              :class="{ valid: !v$.response.$invalid, invalid: v$.response.$invalid }"
              v-model="v$.response.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.processLog.status')"
              for="process-log-status"
            ></label>
            <input
              type="text"
              class="form-control"
              name="status"
              id="process-log-status"
              data-cy="status"
              :class="{ valid: !v$.status.$invalid, invalid: v$.status.$invalid }"
              v-model="v$.status.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.processLog.reason')"
              for="process-log-reason"
            ></label>
            <input
              type="text"
              class="form-control"
              name="reason"
              id="process-log-reason"
              data-cy="reason"
              :class="{ valid: !v$.reason.$invalid, invalid: v$.reason.$invalid }"
              v-model="v$.reason.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.processLog.processStartTime')"
              for="process-log-processStartTime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="process-log-processStartTime"
                  v-model="v$.processStartTime.$model"
                  name="processStartTime"
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
                id="process-log-processStartTime"
                data-cy="processStartTime"
                type="text"
                class="form-control"
                name="processStartTime"
                :class="{ valid: !v$.processStartTime.$invalid, invalid: v$.processStartTime.$invalid }"
                v-model="v$.processStartTime.$model"
              />
            </b-input-group>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.processLog.processEndTime')"
              for="process-log-processEndTime"
            ></label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="process-log-processEndTime"
                  v-model="v$.processEndTime.$model"
                  name="processEndTime"
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
                id="process-log-processEndTime"
                data-cy="processEndTime"
                type="text"
                class="form-control"
                name="processEndTime"
                :class="{ valid: !v$.processEndTime.$invalid, invalid: v$.processEndTime.$invalid }"
                v-model="v$.processEndTime.$model"
              />
            </b-input-group>
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
<script lang="ts" src="./process-log-update.component.ts"></script>
