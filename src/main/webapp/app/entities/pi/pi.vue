<template>
  <div>
    <h2 id="page-heading" data-cy="PIHeading">
      <span v-text="t$('descoursCabaudApplicationApp.pI.home.title')" id="pi-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.pI.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'PICreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-pi">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.pI.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && pIS && pIS.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.pI.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="pIS && pIS.length > 0">
      <table class="table table-striped" aria-describedby="pIS">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.poNumber')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.category')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.client')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.orderDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.requestedEndOfProdDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.countryOfOrigin')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.countryOfFinalDestination')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.consignee')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.carriageBy')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.termsOfDelivery')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.termsOfPayment')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.currency')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.remarks')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.kingdeeId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.portOfDischarge')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.portOfLoading')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.pI.docStatus')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="pI in pIS" :key="pI.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PIView', params: { pIId: pI.id } }">{{ pI.id }}</router-link>
            </td>
            <td>{{ pI.itemName }}</td>
            <td>{{ pI.poNumber }}</td>
            <td>{{ pI.category }}</td>
            <td>{{ pI.client }}</td>
            <td>{{ pI.orderDate }}</td>
            <td>{{ pI.requestedEndOfProdDate }}</td>
            <td>{{ pI.countryOfOrigin }}</td>
            <td>{{ pI.countryOfFinalDestination }}</td>
            <td>{{ pI.consignee }}</td>
            <td>{{ pI.carriageBy }}</td>
            <td>{{ pI.termsOfDelivery }}</td>
            <td>{{ pI.termsOfPayment }}</td>
            <td>{{ pI.currency }}</td>
            <td>{{ pI.remarks }}</td>
            <td>{{ pI.kingdeeId }}</td>
            <td>{{ pI.portOfDischarge }}</td>
            <td>{{ pI.portOfLoading }}</td>
            <td>{{ pI.docStatus }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PIView', params: { pIId: pI.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PIEdit', params: { pIId: pI.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(pI)"
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
          id="descoursCabaudApplicationApp.pI.delete.question"
          data-cy="pIDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p id="jhi-delete-pI-heading" v-text="t$('descoursCabaudApplicationApp.pI.delete.question', { id: removeId })"></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-pI"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removePI()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./pi.component.ts"></script>
