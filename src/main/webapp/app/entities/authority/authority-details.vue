<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="authority">
        <h2 class="jh-entity-heading" data-cy="authorityDetailsHeading">
          <span v-text="t$('descoursCabaudApplicationApp.authority.detail.title')"></span> {{ authority.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="t$('descoursCabaudApplicationApp.authority.name')"></span>
          </dt>
          <dd>
            <span>{{ authority.name }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.back')"></span>
        </button>
        <router-link
          v-if="authority.id"
          :to="{ name: 'AuthorityEdit', params: { authorityId: authority.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="t$('entity.action.edit')"></span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./authority-details.component.ts"></script>
