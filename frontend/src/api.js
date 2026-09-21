export const API=import.meta.env.VITE_API_URL||'http://localhost:8080/api';
export const money=v=>Number(v||0).toLocaleString('pt-BR',{style:'currency',currency:'BRL'});
export const date=v=>v?new Date(v).toLocaleString('pt-BR',{dateStyle:'medium',timeStyle:'short'}):'';
export const shortDate=v=>v?new Date(v).toLocaleDateString('pt-BR',{day:'2-digit',month:'short'}):'';
export async function request(path,opt={}){const r=await fetch(API+path,{headers:{'Content-Type':'application/json'},...opt});if(!r.ok){let x={};try{x=await r.json()}catch{}throw new Error(x.erro||'Não foi possível concluir a operação')}if(r.status===204)return null;return r.json()}
