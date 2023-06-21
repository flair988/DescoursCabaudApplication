<template>
  <div>
    <h2 id="page-heading" data-cy="SupplierQualificationHeading">
      <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.home.title')" id="supplier-qualification-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'SupplierQualificationCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-supplier-qualification"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && supplierQualifications && supplierQualifications.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.supplierQualification.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="supplierQualifications && supplierQualifications.length > 0">
      <table class="table table-striped" aria-describedby="supplierQualifications">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplierQualification.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplierQualification.cateGory')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplierQualification.supplier')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplierQualification.date')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplierQualification.supplierStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplierQualification.evaluationStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplierQualification.businessLiabilityBopeScore')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.supplierQualification.comments')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="supplierQualification in supplierQualifications" :key="supplierQualification.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SupplierQualificationView', params: { supplierQualificationId: supplierQualification.id } }">{{
                supplierQualification.id
              }}</router-link>
            </td>
            <td>{{ supplierQualification.itemName }}</td>
            <td>{{ supplierQualification.cateGory }}</td>
            <td>{{ supplierQualification.supplier }}</td>
            <td>{{ supplierQualification.date }}</td>
            <td>{{ supplierQualification.supplierStatus }}</td>
            <td>{{ supplierQualification.evaluationStatus }}</td>
            <td>{{ supplierQualification.businessLiabilityBopeScore }}</td>
            <td>{{ supplierQualification.comments }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SupplierQualificationView', params: { supplierQualificationId: supplierQualification.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SupplierQualificationEdit', params: { supplierQualificationId: supplierQualification.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(supplierQualification)"
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
          id="descoursCabaudApplicationApp.supplierQualification.delete.question"
          data-cy="supplierQualificationDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-supplierQualification-heading"
          v-text="t$('descoursCabaudApplicationApp.supplierQualification.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-supplierQualification"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSupplierQualification()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./supplier-qualification.component.ts"></script>
