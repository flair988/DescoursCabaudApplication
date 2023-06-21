<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.mondayColumn.home.createOrEditLabel"
          data-cy="MondayColumnCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.mondayColumn.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="mondayColumn.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="mondayColumn.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.mondayColumn.boardId')"
              for="monday-column-boardId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="boardId"
              id="monday-column-boardId"
              data-cy="boardId"
              :class="{ valid: !v$.boardId.$invalid, invalid: v$.boardId.$invalid }"
              v-model="v$.boardId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.mondayColumn.columnId')"
              for="monday-column-columnId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="columnId"
              id="monday-column-columnId"
              data-cy="columnId"
              :class="{ valid: !v$.columnId.$invalid, invalid: v$.columnId.$invalid }"
              v-model="v$.columnId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.mondayColumn.title')"
              for="monday-column-title"
            ></label>
            <input
              type="text"
              class="form-control"
              name="title"
              id="monday-column-title"
              data-cy="title"
              :class="{ valid: !v$.title.$invalid, invalid: v$.title.$invalid }"
              v-model="v$.title.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.mondayColumn.type')"
              for="monday-column-type"
            ></label>
            <input
              type="text"
              class="form-control"
              name="type"
              id="monday-column-type"
              data-cy="type"
              :class="{ valid: !v$.type.$invalid, invalid: v$.type.$invalid }"
              v-model="v$.type.$model"
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
<script lang="ts" src="./monday-column-update.component.ts"></script>
