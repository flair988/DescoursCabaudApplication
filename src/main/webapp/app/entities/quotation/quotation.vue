<template>
  <div>
    <h2 id="page-heading" data-cy="QuotationHeading">
      <span v-text="t$('descoursCabaudApplicationApp.quotation.home.title')" id="quotation-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.quotation.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'QuotationCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-quotation"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.quotation.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && quotations && quotations.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.quotation.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="quotations && quotations.length > 0">
      <table class="table table-striped" aria-describedby="quotations">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.quotation.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.quotation.quotationDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.quotation.inquiryDocNumber')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.quotation.docNo')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.quotation.docStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.quotation.supplier')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.quotation.kingdeeId')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="quotation in quotations" :key="quotation.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'QuotationView', params: { quotationId: quotation.id } }">{{ quotation.id }}</router-link>
            </td>
            <td>{{ quotation.itemName }}</td>
            <td>{{ quotation.quotationDate }}</td>
            <td>{{ quotation.inquiryDocNumber }}</td>
            <td>{{ quotation.docNo }}</td>
            <td>{{ quotation.docStatus }}</td>
            <td>{{ quotation.supplier }}</td>
            <td>{{ quotation.kingdeeId }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'QuotationView', params: { quotationId: quotation.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'QuotationEdit', params: { quotationId: quotation.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(quotation)"
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
          id="descoursCabaudApplicationApp.quotation.delete.question"
          data-cy="quotationDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-quotation-heading" v-text="t$('descoursCabaudApplicationApp.quotation.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-quotation"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeQuotation()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./quotation.component.ts"></script>
