<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.forwarder.home.createOrEditLabel"
          data-cy="ForwarderCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.forwarder.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="forwarder.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="forwarder.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarder.itemName')"
              for="forwarder-itemName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="forwarder-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.forwarder.itemId')" for="forwarder-itemId"></label>
            <input
              type="text"
              class="form-control"
              name="itemId"
              id="forwarder-itemId"
              data-cy="itemId"
              :class="{ valid: !v$.itemId.$invalid, invalid: v$.itemId.$invalid }"
              v-model="v$.itemId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.forwarder.boardId')" for="forwarder-boardId"></label>
            <input
              type="text"
              class="form-control"
              name="boardId"
              id="forwarder-boardId"
              data-cy="boardId"
              :class="{ valid: !v$.boardId.$invalid, invalid: v$.boardId.$invalid }"
              v-model="v$.boardId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarder.kingdeeId')"
              for="forwarder-kingdeeId"
            ></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="forwarder-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.forwarder.contact')" for="forwarder-contact"></label>
            <input
              type="text"
              class="form-control"
              name="contact"
              id="forwarder-contact"
              data-cy="contact"
              :class="{ valid: !v$.contact.$invalid, invalid: v$.contact.$invalid }"
              v-model="v$.contact.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.forwarder.email')" for="forwarder-email"></label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="forwarder-email"
              data-cy="email"
              :class="{ valid: !v$.email.$invalid, invalid: v$.email.$invalid }"
              v-model="v$.email.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.forwarder.telephone')"
              for="forwarder-telephone"
            ></label>
            <input
              type="text"
              class="form-control"
              name="telephone"
              id="forwarder-telephone"
              data-cy="telephone"
              :class="{ valid: !v$.telephone.$invalid, invalid: v$.telephone.$invalid }"
              v-model="v$.telephone.$model"
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
<script lang="ts" src="./forwarder-update.component.ts"></script>
