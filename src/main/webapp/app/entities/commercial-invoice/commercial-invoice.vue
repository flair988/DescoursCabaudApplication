<template>
  <div>
    <h2 id="page-heading" data-cy="CommercialInvoiceHeading">
      <span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.home.title')" id="commercial-invoice-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'CommercialInvoiceCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-commercial-invoice"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && commercialInvoices && commercialInvoices.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="commercialInvoices && commercialInvoices.length > 0">
      <table class="table table-striped" aria-describedby="commercialInvoices">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.kingdeeId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.date')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.client')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.cateGory')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.totalPrice')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.currency')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.remarks')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.clientId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.commercialInvoice.docStatus')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="commercialInvoice in commercialInvoices" :key="commercialInvoice.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CommercialInvoiceView', params: { commercialInvoiceId: commercialInvoice.id } }">{{
                commercialInvoice.id
              }}</router-link>
            </td>
            <td>{{ commercialInvoice.itemName }}</td>
            <td>{{ commercialInvoice.kingdeeId }}</td>
            <td>{{ commercialInvoice.date }}</td>
            <td>{{ commercialInvoice.client }}</td>
            <td>{{ commercialInvoice.cateGory }}</td>
            <td>{{ commercialInvoice.totalPrice }}</td>
            <td>{{ commercialInvoice.currency }}</td>
            <td>{{ commercialInvoice.remarks }}</td>
            <td>{{ commercialInvoice.clientId }}</td>
            <td>{{ commercialInvoice.docStatus }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'CommercialInvoiceView', params: { commercialInvoiceId: commercialInvoice.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'CommercialInvoiceEdit', params: { commercialInvoiceId: commercialInvoice.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(commercialInvoice)"
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
          id="descoursCabaudApplicationApp.commercialInvoice.delete.question"
          data-cy="commercialInvoiceDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-commercialInvoice-heading"
          v-text="t$('descoursCabaudApplicationApp.commercialInvoice.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-commercialInvoice"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeCommercialInvoice()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./commercial-invoice.component.ts"></script>
