<template>
  <div>
    <h2 id="page-heading" data-cy="UomHeading">
      <span v-text="t$('descoursCabaudApplicationApp.uom.home.title')" id="uom-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.uom.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'UomCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-uom">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.uom.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && uoms && uoms.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.uom.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="uoms && uoms.length > 0">
      <table class="table table-striped" aria-describedby="uoms">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.uom.itemId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.uom.uom')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.uom.uomGroup')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.uom.description')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.uom.subItems')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.uom.parentItem')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.uom.boardId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.uom.kingdeeId')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="uom in uoms" :key="uom.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'UomView', params: { uomId: uom.id } }">{{ uom.id }}</router-link>
            </td>
            <td>{{ uom.itemId }}</td>
            <td>{{ uom.uom }}</td>
            <td>{{ uom.uomGroup }}</td>
            <td>{{ uom.description }}</td>
            <td>{{ uom.subItems }}</td>
            <td>{{ uom.parentItem }}</td>
            <td>{{ uom.boardId }}</td>
            <td>{{ uom.kingdeeId }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'UomView', params: { uomId: uom.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'UomEdit', params: { uomId: uom.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(uom)"
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
          id="descoursCabaudApplicationApp.uom.delete.question"
          data-cy="uomDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-uom-heading" v-text="t$('descoursCabaudApplicationApp.uom.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-uom"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeUom()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./uom.component.ts"></script>
