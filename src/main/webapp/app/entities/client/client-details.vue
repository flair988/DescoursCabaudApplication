<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="client">
        <h2 class="jh-entity-heading" data-cy="clientDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.client.detail.title')"></span> {{ client.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.client.itemId')"></span>
          </dt>
          <dd>
            <span>{{ client.itemId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.client.itemName')"></span>
          </dt>
          <dd>
            <span>{{ client.itemName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.client.boardId')"></span>
          </dt>
          <dd>
            <span>{{ client.boardId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.client.subItems')"></span>
          </dt>
          <dd>
            <span>{{ client.subItems }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.client.kingdeeId')"></span>
          </dt>
          <dd>
            <span>{{ client.kingdeeId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.client.customerName')"></span>
          </dt>
          <dd>
            <span>{{ client.customerName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.client.customerCode')"></span>
          </dt>
          <dd>
            <span>{{ client.customerCode }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.client.customerFrenceName')"></span>
          </dt>
          <dd>
            <span>{{ client.customerFrenceName }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.client.comment')"></span>
          </dt>
          <dd>
            <span>{{ client.comment }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="client.id" :to="{ name: 'ClientEdit', params: { clientId: client.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./client-details.component.ts"></script>
