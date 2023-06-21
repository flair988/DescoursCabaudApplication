<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.group.home.createOrEditLabel"
          data-cy="GroupCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.group.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="group.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="group.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.group.groupId')" for="group-groupId"></label>
            <input
              type="number"
              class="form-control"
              name="groupId"
              id="group-groupId"
              data-cy="groupId"
              :class="{ valid: !v$.groupId.$invalid, invalid: v$.groupId.$invalid }"
              v-model.number="v$.groupId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.group.groupNumber')" for="group-groupNumber"></label>
            <input
              type="text"
              class="form-control"
              name="groupNumber"
              id="group-groupNumber"
              data-cy="groupNumber"
              :class="{ valid: !v$.groupNumber.$invalid, invalid: v$.groupNumber.$invalid }"
              v-model="v$.groupNumber.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.group.parentId')" for="group-parentId"></label>
            <input
              type="number"
              class="form-control"
              name="parentId"
              id="group-parentId"
              data-cy="parentId"
              :class="{ valid: !v$.parentId.$invalid, invalid: v$.parentId.$invalid }"
              v-model.number="v$.parentId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.group.groupName')" for="group-groupName"></label>
            <input
              type="text"
              class="form-control"
              name="groupName"
              id="group-groupName"
              data-cy="groupName"
              :class="{ valid: !v$.groupName.$invalid, invalid: v$.groupName.$invalid }"
              v-model="v$.groupName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.group.description')" for="group-description"></label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="group-description"
              data-cy="description"
              :class="{ valid: !v$.description.$invalid, invalid: v$.description.$invalid }"
              v-model="v$.description.$model"
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
<script lang="ts" src="./group-update.component.ts"></script>
