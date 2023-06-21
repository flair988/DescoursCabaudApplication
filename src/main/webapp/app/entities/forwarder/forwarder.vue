<template>
  <div>
    <h2 id="page-heading" data-cy="ForwarderHeading">
      <span v-text="t$('descoursCabaudApplicationApp.forwarder.home.title')" id="forwarder-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.forwarder.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'ForwarderCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-forwarder"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.forwarder.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && forwarders && forwarders.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.forwarder.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="forwarders && forwarders.length > 0">
      <table class="table table-striped" aria-describedby="forwarders">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.forwarder.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.forwarder.itemId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.forwarder.boardId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.forwarder.kingdeeId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.forwarder.contact')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.forwarder.email')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.forwarder.telephone')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="forwarder in forwarders" :key="forwarder.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ForwarderView', params: { forwarderId: forwarder.id } }">{{ forwarder.id }}</router-link>
            </td>
            <td>{{ forwarder.itemName }}</td>
            <td>{{ forwarder.itemId }}</td>
            <td>{{ forwarder.boardId }}</td>
            <td>{{ forwarder.kingdeeId }}</td>
            <td>{{ forwarder.contact }}</td>
            <td>{{ forwarder.email }}</td>
            <td>{{ forwarder.telephone }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ForwarderView', params: { forwarderId: forwarder.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ForwarderEdit', params: { forwarderId: forwarder.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(forwarder)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="t$('entity.action.delete')"></span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <template #modal-title>
        <span
          id="descoursCabaudApplicationApp.forwarder.delete.question"
          data-cy="forwarderDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-forwarder-heading" v-text="t$('descoursCabaudApplicationApp.forwarder.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-forwarder"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeForwarder()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./forwarder.component.ts"></script>
