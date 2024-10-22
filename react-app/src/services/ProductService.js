import axios from "axios";

const initProducts = [
    {
        id: 1,
        name: 'Monitor Samsung ',
        price: 500,
        description: 'El monitor es increíble!'
    },
    {
        id: 2,
        name: 'Iphone ',
        price: 600,
        description: 'El iphone es increíble!'
    }
];

const baseUrl = 'http://localhost:8080/products';

export const listProduct = () => { 
    return initProducts;
}

export const findAll = async () => {    // await must use async
    try {
        const response = await axios.get(baseUrl);
        return response;
    } catch (err) {
        console.error(err);
    }
    return null;
}

export const create = async ({name, price, description}) => {
    try {
        const response = await axios.post(baseUrl, { name, price, description });
        return response;
    } catch (err) {
        console.error(err);
    }
    return undefined;
}

export const update = async ({ id, name, price, description }) => {
    try {
        const response = await axios.put(baseUrl+"/"+id, { name, price, description });
        return response;
    } catch (err) {
        console.error(err);
    }
    return undefined;
}

export const remove = async (id) => {
    try {
        await axios.delete(baseUrl + "/" + id);
    } catch (err) {
        console.log(err);
    }
}