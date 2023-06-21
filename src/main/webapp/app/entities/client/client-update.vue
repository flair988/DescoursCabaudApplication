<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="descoursCabaudApplicationApp.client.home.createOrEditLabel"
          data-cy="ClientCreateUpdateHeading"
          v-text="t$('descoursCabaudApplicationApp.client.home.createOrEditLabel')"
        ></h2>
        <div>
          <div class="form-group" v-if="client.id">
            <label for="id" v-text="t$('global.field.id')"></label>
            <input type="text" class="form-control" id="id" name="id" v-model="client.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.client.itemId')" for="client-itemId"></label>
            <input
              type="text"
              class="form-control"
              name="itemId"
              id="client-itemId"
              data-cy="itemId"
              :class="{ valid: !v$.itemId.$invalid, invalid: v$.itemId.$invalid }"
              v-model="v$.itemId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.client.itemName')" for="client-itemName"></label>
            <input
              type="text"
              class="form-control"
              name="itemName"
              id="client-itemName"
              data-cy="itemName"
              :class="{ valid: !v$.itemName.$invalid, invalid: v$.itemName.$invalid }"
              v-model="v$.itemName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.client.boardId')" for="client-boardId"></label>
            <input
              type="text"
              class="form-control"
              name="boardId"
              id="client-boardId"
              data-cy="boardId"
              :class="{ valid: !v$.boardId.$invalid, invalid: v$.boardId.$invalid }"
              v-model="v$.boardId.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.client.subItems')" for="client-subItems"></label>
            <input
              type="text"
              class="form-control"
              name="subItems"
              id="client-subItems"
              data-cy="subItems"
              :class="{ valid: !v$.subItems.$invalid, invalid: v$.subItems.$invalid }"
              v-model="v$.subItems.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.client.kingdeeId')" for="client-kingdeeId"></label>
            <input
              type="text"
              class="form-control"
              name="kingdeeId"
              id="client-kingdeeId"
              data-cy="kingdeeId"
              :class="{ valid: !v$.kingdeeId.$invalid, invalid: v$.kingdeeId.$invalid }"
              v-model="v$.kingdeeId.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.client.customerName')"
              for="client-customerName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="customerName"
              id="client-customerName"
              data-cy="customerName"
              :class="{ valid: !v$.customerName.$invalid, invalid: v$.customerName.$invalid }"
              v-model="v$.customerName.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.client.customerCode')"
              for="client-customerCode"
            ></label>
            <input
              type="text"
              class="form-control"
              name="customerCode"
              id="client-customerCode"
              data-cy="customerCode"
              :class="{ valid: !v$.customerCode.$invalid, invalid: v$.customerCode.$invalid }"
              v-model="v$.customerCode.$model"
            />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="t$('descoursCabaudApplicationApp.client.customerFrenceName')"
              for="client-customerFrenceName"
            ></label>
            <input
              type="text"
              class="form-control"
              name="customerFrenceName"
              id="client-customerFrenceName"
              data-cy="customerFrenceName"
              :class="{ valid: !v$.customerFrenceName.$invalid, invalid: v$.customerFrenceName.$invalid }"
              v-model="v$.customerFrenceName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="t$('descoursCabaudApplicationApp.client.comment')" for="client-comment"></label>
            <input
              type="text"
              class="form-control"
              name="comment"
              id="client-comment"
              data-cy="comment"
              :class="{ valid: !v$.comment.$invalid, invalid: v$.comment.$invalid }"
              v-model="v$.comment.$model"
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
<script lang="ts" src="./client-update.component.ts"></script>
