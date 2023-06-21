<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.mondayUser.home.createOrEditLabel"
          data-cy="MondayUserCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.mondayUser.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="mondayUser.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="mondayUser.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.mondayUser.mondayId')"
              for="monday-user-mondayId"
            ></label>
            <input
              type="number"
              class="form-control"
              name="mondayId"
              id="monday-user-mondayId"
              data-cy="mondayId"
              :class="{ valid: !v$.mondayId.$invalid, invalid: v$.mondayId.$invalid }"
              v-model.number="v$.mondayId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.mondayUser.name')" for="monday-user-name"></label>
            <input
              type="text"
              class="form-control"
              name="name"
              id="monday-user-name"
              data-cy="name"
              :class="{ valid: !v$.name.$invalid, invalid: v$.name.$invalid }"
              v-model="v$.name.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.mondayUser.email')" for="monday-user-email"></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="monday-user-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.mondayUser.url')" for="monday-user-url"></label>
            <input
              type="text"
              class="form-control"
              name="url"
              id="monday-user-url"
              data-cy="url"
              :class="{ valid: !v$.url.$invalid, invalid: v$.url.$invalid }"
              v-model="v$.url.$model"
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
<script lang="ts" src="./monday-user-update.component.ts"></script>
