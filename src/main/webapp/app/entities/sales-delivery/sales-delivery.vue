<template>
  <div>
    <h2 id="page-heading" data-cy="SalesDeliveryHeading">
      <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.home.title')" id="sales-delivery-heading"></span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.home.refreshListLabel')"></span>
        </button>
        <router-link :to="{ name: 'SalesDeliveryCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-sales-delivery"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.home.createLabel')"></span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && salesDeliveries && salesDeliveries.length === 0">
      <span v-text="t$('descoursCabaudApplicationApp.salesDelivery.home.notFound')"></span>
    </div>
    <div class="table-responsive" v-if="salesDeliveries && salesDeliveries.length > 0">
      <table class="table table-striped" aria-describedby="salesDeliveries">
        <thead>
          <tr>
            <th scope="row"><span v-text="t$('global.field.id')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.itemName')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.kingdeeId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.customer')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.orderDate')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.loadingPort')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.dischargePort')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.transportMode')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.incoterm')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.forwarder')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.eta')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.etd')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.containerType')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.containerSize')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.remark')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.kingdeeUniqueId')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.docStatus')"></span></th>
            <th scope="row"><span v-text="t$('descoursCabaudApplicationApp.salesDelivery.cateGory')"></span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="salesDelivery in salesDeliveries" :key="salesDelivery.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'SalesDeliveryView', params: { salesDeliveryId: salesDelivery.id } }">{{
                salesDelivery.id
              }}</router-link>
            </td>
            <td>{{ salesDelivery.itemName }}</td>
            <td>{{ salesDelivery.kingdeeId }}</td>
            <td>{{ salesDelivery.customer }}</td>
            <td>{{ salesDelivery.orderDate }}</td>
            <td>{{ salesDelivery.loadingPort }}</td>
            <td>{{ salesDelivery.dischargePort }}</td>
            <td>{{ salesDelivery.transportMode }}</td>
            <td>{{ salesDelivery.incoterm }}</td>
            <td>{{ salesDelivery.forwarder }}</td>
            <td>{{ salesDelivery.eta }}</td>
            <td>{{ salesDelivery.etd }}</td>
            <td>{{ salesDelivery.containerType }}</td>
            <td>{{ salesDelivery.containerSize }}</td>
            <td>{{ salesDelivery.remark }}</td>
            <td>{{ salesDelivery.kingdeeUniqueId }}</td>
            <td>{{ salesDelivery.docStatus }}</td>
            <td>{{ salesDelivery.cateGory }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'SalesDeliveryView', params: { salesDeliveryId: salesDelivery.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.view')"></span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'SalesDeliveryEdit', params: { salesDeliveryId: salesDelivery.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="t$('entity.action.edit')"></span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(salesDelivery)"
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
          id="descoursCabaudApplicationApp.salesDelivery.delete.question"
          data-cy="salesDeliveryDeleteDialogHeading"
          v-text="t$('entity.delete.title')"
        ></span>
      </template>
      <div class="modal-body">
        <p
          id="jhi-delete-salesDelivery-heading"
          v-text="t$('descoursCabaudApplicationApp.salesDelivery.delete.question', { id: removeId })"
        ></p>
      </div>
      <template #modal-footer>
        <div>
          <button type="button" class="btn btn-secondary" v-text="t$('entity.action.cancel')" v-on:click="closeDialog()"></button>
          <button
            type="button"
            class="btn btn-primary"
            id="jhi-confirm-delete-salesDelivery"
            data-cy="entityConfirmDeleteButton"
            v-text="t$('entity.action.delete')"
            v-on:click="removeSalesDelivery()"
          ></button>
        </div>
      </template>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./sales-delivery.component.ts"></script>
