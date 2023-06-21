<template>
  <div>
    <h2 id="page-heading" data-cy="InspectionHeading">
      <span v-text="t$('descoursCabaudApplicationApp.inspection.home.title')" id="inspection-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.inspection.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'InspectionCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-inspection"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.inspection.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && inspections && inspections.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.inspection.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="inspections && inspections.length > 0">
      <table class="table table-striped" aria-describedby="inspections">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.kingdeeId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.supplierName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.email')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.inspectionDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.cateGory')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.qCResult')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.docStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.inspectionType')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.inspectionBookingStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.inspectionEndDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.supplierId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.inspection.reportNumber')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="inspection in inspections" :key="inspection.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'InspectionView', params: { inspectionId: inspection.id } }">{{ inspection.id }}</router-link>
            </td>
            <td>{{ inspection.itemName }}</td>
            <td>{{ inspection.kingdeeId }}</td>
            <td>{{ inspection.supplierName }}</td>
            <td>{{ inspection.email }}</td>
            <td>{{ inspection.inspectionDate }}</td>
            <td>{{ inspection.cateGory }}</td>
            <td>{{ inspection.qCResult }}</td>
            <td>{{ inspection.docStatus }}</td>
            <td>{{ inspection.inspectionType }}</td>
            <td>{{ inspection.inspectionBookingStatus }}</td>
            <td>{{ inspection.inspectionEndDate }}</td>
            <td>{{ inspection.supplierId }}</td>
            <td>{{ inspection.reportNumber }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'InspectionView', params: { inspectionId: inspection.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'InspectionEdit', params: { inspectionId: inspection.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(inspection)"
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
          id="descoursCabaudApplicationApp.inspection.delete.question"
          data-cy="inspectionDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-inspection-heading" v-text="t$('descoursCabaudApplicationApp.inspection.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-inspection"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeInspection()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./inspection.component.ts"></script>
