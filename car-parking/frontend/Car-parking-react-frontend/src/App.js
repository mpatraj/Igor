import React, {Component} from 'react';
import './App.css';
import axios from 'axios'

class App extends Component{
    constructor(props) {
        super(props);
        this.state = {

            userID: 12,
            carNum: '',
            //date: new Date().toLocaleString()
        }
    }

    changeHandler = (e) => {
        this.setState({[e.target.name]: e.target.value})
    }
    submitHandler = e => {
        e.preventDefault()
        axios.get('/api/v1/person/')
            .then(response =>{
                console.log(response)
                // this.setState({posts: response.data})
            })
            .catch(error =>{
                console.log(error)
                // this.setState({errorMsg: 'Error retreiving data'})
            })
    }

    render() {
        const {userID, carNum} = this.state
        return(

            <div className="App container">
                <div className="container">
                    <div className="py-5 text-center">
                        <img className="d-block mx-auto mb-2"
                             src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Feature_parking.svg/600px-Feature_parking.svg.png"
                             alt="" width="72" height="72"/>
                            <h2>Parking system&#8203;</h2>
                            <p className="lead">Register your car or change number fast and easy</p>
                    </div>
                    <div className="row justify-content-center">
                        <div className="col-md-4 order-md-2 mb-2">
                            <h4 className="d-flex justify-content-between align-items-center mb-3">
                                <span className="text-muted">Your parking history</span>
                                <span className="badge badge-secondary badge-pill" id="counter">0</span>
                            </h4>
                            <ul className="list-group mb-3">
                                <li className="list-group-item d-flex justify-content-between lh-condensed">
                                    <div>
                                        <h6 className="my-0">Mukusalas BC zone 1</h6>
                                        <small className="text-muted">21.06.2020.</small>
                                    </div>
                                    <span className="text-muted">FG3456</span>
                                </li>
                                <li className="list-group-item d-flex justify-content-between lh-condensed">
                                    <div>
                                        <h6 className="my-0">Mukusalas BC zone 2</h6>
                                        <small className="text-muted">26.06.2020.</small>
                                    </div>
                                    <span className="text-muted">HD3457</span>
                                </li>
                                <li className="list-group-item d-flex justify-content-between lh-condensed">
                                    <div>
                                        <h6 className="my-0">Mukusalas BC zone 1</h6>
                                        <small className="text-muted">27.06.2020.</small>
                                    </div>
                                    <span className="text-muted">FG3456</span>
                                </li>
                                <li className="list-group-item d-flex justify-content-between bg-light">
                                    <div className="text-success">
                                        <h6 className="my-0">Mukusalas BC zone X</h6>
                                        <small>15.07.2020.</small>
                                    </div>

                                </li>
                            </ul>
                            <div className="input-group">
                                <form className="input-group" id="new-task">
                                    <input id="task" autoComplete="off" autoFocus placeholder="XX1111" type="text"
                                           className="form-control"/>
                                    <div className="input-group-append">
                                        <button id="submit" type="submit" className="btn btn-secondary">Add</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div className="col-md-4 order-md-1">
                            <h4 className="d-flex justify-content-between align-items-center mb-3">
                                <span className="text-muted">Add car</span>
                            </h4>

                            <form className="needs-validation" noValidate="" onSubmit={this.submitHandler}>

                                <div className="mb-3">
                                    <label htmlFor="email">Employee ID</label>
                                    <input type="text" className="form-control" placeholder="your ID number" name="userID"  onChange={this.changeHandler} />
                                </div>

                                <div className="mb-3">
                                    <label htmlFor="email"> Car number </label>
                                    <input type="text" className="form-control" placeholder="your car number" minLength="4" maxLength="8" name="carNum" value={carNum} onChange={this.changeHandler} />
                                </div>
                                <div className="custom-control custom-checkbox">
                                    <input type="checkbox" className="custom-control-input" id="save-info"/>
                                    <label className="custom-control-label" htmlFor="save-info">Agree with
                                        <a href="https://www.terms.com/"> terms and conditions</a> </label>

                                </div>
                                <hr className="mb-4"/>

                                <button className="btn btn-primary btn-lg btn-lg" type={"submit"}>Submit</button>


                            </form>
                        </div>
                    </div>
                    <footer className="my-5 pt-5 text-muted text-center text-small">
                        <p className="mb-1">© 2020 AppZilla</p>
                        <ul className="list-inline">
                            <li className="list-inline-item"><a href="https://privacy.com">Privacy</a></li>
                            <li className="list-inline-item"><a href="https://privacy.com">Terms</a></li>
                            <li className="list-inline-item"><a href="https://privacy.com">Support</a></li>
                        </ul>
                    </footer>
                </div>
            </div>
        );

    }
}


export default App;


