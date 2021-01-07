import axios from "axios";

const API_URL = "https://ds2-delivery.herokuapp.com";

export function fetchProducts(){
  return axios(`${API_URL}/products`)
}