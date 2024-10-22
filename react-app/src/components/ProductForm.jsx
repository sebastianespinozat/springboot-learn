import { useEffect, useState } from "react"

const initialDataForm = {
    id: 0,
    name: "",
    price: "",
    description: ""
}

export const ProductForm = ({productSelected, handlerAdd}) => {

    const [form, setForm] = useState(initialDataForm);

    const { id, name, price, description } = form;

    useEffect(() => {
        setForm(productSelected);
    }, [productSelected]);

    return (
        <form onSubmit={(event) => {
            event.preventDefault(); // que al enviar el formulario se quede en la pagina
            
            if (!name || !price || !description) {
                alert("Debe de completar los datos del formulario!")
                return;
            }

            //console.log(form);
            handlerAdd(form);
            setForm(initialDataForm);   // deja vacio el formulario luego de enviar un dato
        }}>
            <div>
                <input placeholder="name"
                    className="form-control my-3 w-75"
                    name="name"
                    value={ name }
                    onChange={(event) => setForm({
                        ...form,
                        name: event.target.value                 
                    })}
                />
            </div>
            <div>
                <input placeholder="price"
                    className="form-control my-3 w-75"
                    name="price"
                    value={ price }
                    onChange={(event) => setForm({
                        ...form,
                        price: event.target.value
                    })}
                />
            </div>
            <div>
                <input placeholder="description"
                    className="form-control my-3 w-75"
                    name="description"
                    value={ description }
                    onChange={(event) => setForm({
                        ...form,
                        description: event.target.value
                    })}
                />
            </div>
            <button type="submit" className="btn btn-primary">
                {id > 0 ? 'Update': 'Create'}
            </button>
        </form>
    )
}