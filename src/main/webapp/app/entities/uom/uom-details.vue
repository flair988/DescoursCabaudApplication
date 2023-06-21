<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="uom">
        <h2 class="jh-entity-heading" data-cy="uomDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.uom.detail.title')"></span> {{ uom.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.uom.itemId')"></span>
          </dt>
          <dd>
            <span>{{ uom.itemId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.uom.uom')"></span>
          </dt>
          <dd>
            <span>{{ uom.uom }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.uom.uomGroup')"></span>
          </dt>
          <dd>
            <span>{{ uom.uomGroup }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.uom.description')"></span>
          </dt>
          <dd>
            <span>{{ uom.description }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.uom.subItems')"></span>
          </dt>
          <dd>
            <span>{{ uom.subItems }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.uom.parentItem')"></span>
          </dt>
          <dd>
            <span>{{ uom.parentItem }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.uom.boardId')"></span>
          </dt>
          <dd>
            <span>{{ uom.boardId }}</span>
          </dd>
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.uom.kingdeeId')"></span>
          </dt>
          <dd>
            <span>{{ uom.kingdeeId }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link v-if="uom.id" :to="{ name: 'UomEdit', params: { uomId: uom.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./uom-details.component.ts"></script>
